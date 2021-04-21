import './App.css';
import React from 'react';
import 'bulma/css/bulma.min.css';

class CityFilter extends React.Component {

    constructor(props) {
        super(props);
        this.handleFilterByCityChange = this.handleFilterByCityChange.bind(this);
    }

    handleFilterByCityChange(e) {
        console.log(e.target.value);
        this.props.onFilterByCityChange(e.target.value);
    }

    /**
     * 
     * @returns Render City Selection based filter.
     */
    render() {
        return <div className="field is-horizontal">
            <div className="field-label is-normal">
                <label className="label">City</label>
            </div>
            <div className="field-body">
                <div className="field is-narrow">
                    <div className="control">
                        <div className="select">
                            <select className="select" onChange={this.handleFilterByCityChange}>
                                <option value="">None</option>
                                <option>New York</option>
                                <option>Chicago</option>
                                <option>San Francisco</option>
                                <option>Phoenix</option>
                                <option>London</option>
                                <option>Beijing</option>
                                <option>Paris</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>;
    }
}

class JobDescriptionFilter extends React.Component {

    constructor(props) {
        super(props);
        this.handleFilterByLangChange = this.handleFilterByLangChange.bind(this);
    }

    handleFilterByLangChange(e) {
        console.log(e.target.value);
        this.props.onFilterByLangChange(e.target.value);
    }

    render() {
        return <div id="job-div" className="field is-horizontal">
            <div className="field-label is-normal">
                <label className="label">Description</label>
            </div>
            <div className="field-body">
                <div className="field is-narrow">
                    <div className="control">
                        <div className="select">
                            <select onChange={this.handleFilterByLangChange}>
                                <option value="">None</option>
                                <option>Javascript</option>
                                <option>Java</option>
                                <option>Python</option>
                                <option>React</option>
                                <option>Ruby</option>
                                <option>Go</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>;
    }
}

class JobList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            items: [],
        };
    }

    componentDidUpdate(prevProps) {
        // Typical usage (don't forget to compare props):
        var search = false;
        if (this.props.filterByCity !== prevProps.filterByCity) {
            search = true;
        }
        
        if(this.props.filterByLang !== prevProps.filterByLang){
            search = true;
        }

        if(search === true){
            var filterString = '?location=' + this.props.filterByCity
                 + '&description=' + this.props.filterByLang;
            this.fetchJobs(filterString);
        }
    }

    fetchJobs(filterBy) {
        
        var url = "/api/jobs" + (filterBy.length > 0 ?  filterBy : '');
        console.log(url);
        fetch(url)
            .then(res => res.json())
            .then(
                (result) => {
                    //this.props.jobDetails = result;
                    
                    this.setState({
                        isLoaded: true,
                        items: result
                    });
                },
                // Note: it's important to handle errors here
                // instead of a catch() block so that we don't swallow
                // exceptions from actual bugs in components.
                (error) => {
                    console.log(error);
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        const rows = [];

        this.state.items.forEach((jobDetail) => {
            rows.push(
                <JobEntry job={jobDetail} 
                        key={jobDetail.id} />
            );
            //rows.push(<p>{jobDetail.title}</p>);
        })

        return <div className="container">
    
            {rows}
            
        </div>;
    }
}

class JobEntry extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const job = this.props.job;

        return <div id="job-card-parent">
            <div  className="card">
                <header id="job-card" className="card-header">
                    <p className="card-header-title">
                        <a href={job.url} target="_blank"> {job.title} </a>
                    </p>
                    <p className="job-tags">
                        <span className="tag is-info">{job.location}</span>
                    </p>
                    <p className="job-tags">
                        <span className="tag is-info">{job.type}</span>
                    </p>
                </header>
                <div className="card-content">
                    <div className="content">
                        <h5> {job.company} </h5>
                        <p dangerouslySetInnerHTML={{__html: job.how_to_apply}} />
                    </div>
                </div>
                <footer className="card-footer">
                    <a href="#" className="card-footer-item">
                        Posted {job.created_at} ago.
                    </a>
                </footer>
            </div>
        </div>;
    }
}

class JobSearch extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            filterByCity: '',
            filterByLang: ''
        };

        this.handleFilterByCityChange = this.handleFilterByCityChange.bind(this);
        this.handleFilterByLangChange = this.handleFilterByLangChange.bind(this);
    }

    handleFilterByCityChange(filterByCity) {
        this.setState({
            filterByCity: filterByCity
        });
    }

    handleFilterByLangChange(filterByLang) {
        this.setState({
            filterByLang: filterByLang
        })
    }

    /**
     * 
     * @returns Render All UI components.
     */
    render() {
        return (
            <div className="container">
                <div className="field is-horizontal">
                <CityFilter filterByCity={this.state.filterByCity}
                        onFilterByCityChange={this.handleFilterByCityChange}
                />
                <JobDescriptionFilter filterByLang={this.state.filterByLang} 
                    onFilterByLangChange={this.handleFilterByLangChange}
                />
                </div>
                <JobList jobDetails={this.props.jobDetails} 
                    filterByCity={this.state.filterByCity}
                    filterByLang={this.state.filterByLang} />
                
            </div>
        );
    }
}

function App() {
    return (
        <section className="section">
            <div className="container">
                <JobSearch />
            </div>
        </section>
    );
}

export default App;
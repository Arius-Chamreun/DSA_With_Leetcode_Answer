export const Header = (props) => {
    return (
        <header className="header">
            <div className="container">
                <h3 style={{color : "white"}}>Contact List({props.nOfContact})</h3>
                <button onClick={() => props.toggleModel(true)} className="btn">
                    <i className="bi bi-plus-circle"> Add New Contact</i>
                </button>
            </div>
        </header>
    )
}
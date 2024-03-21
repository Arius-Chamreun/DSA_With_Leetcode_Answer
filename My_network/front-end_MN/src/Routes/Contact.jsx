import {Link} from "react-router-dom";
export const Contact = (props) => {


        function bufferToImage(buffer) {
            return `data:image/avif;base64,${buffer}`;
        }


    return (
        <Link to={`${props.contacts.id}`} className="contact__item">
            <div className="contact__header">
                <div className="contact__image">
                    <img src={bufferToImage(props.contacts.profile)} alt="image"/>
                </div>
                <div className="contact__body">
                    <p className="contact_name">{props.contacts.name}</p>
                    <p className="contact_title">{props.contacts.jobTitle.toUpperCase()}</p>
                </div>

            </div>
            <div className="contact__body">
                <p><i className="bi bi-envelope"></i>{props.contacts.email}</p>
                <p><i className="bi bi-geo"></i>{props.contacts.companyLocation}</p>
                <p><i className="bi bi-telephone"></i>{props.contacts.phoneNumber}</p>
                <p className="">
                  {props.contacts.stillInContact === "ACTIVE" ?
                      <i className="bi bi-circle-fill"></i> : <i className="bi bi-circle"></i>}
                    {props.contacts.stillInContact} </p>
            </div>
        </Link>

    )
}
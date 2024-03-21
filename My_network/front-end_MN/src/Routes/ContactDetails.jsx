import {useEffect, useRef, useState} from "react";
import {Link, useParams} from "react-router-dom";
import {getContact, updateContact, uploadImage} from "../data/ApiEndPoint.js";

const ContactDetails = () => {

        const {id} = useParams()
        const contactId = parseInt(id)
        const inputRef = useRef()
        const [contact, setContact] =  useState({
            name : "",
            email : "",
            phoneNumber : "",
            companyLocation : "",
            companyName : "",
            jobTitle : "",
            profile : undefined,
            stillInContact : "ACTIVE"
        }
    )
    async function getContactInfo(id) {
            try{
            const {data} = await getContact(id)
             setContact(data)
        }catch (error){
            console.log(error.message)
        }
    }

    useEffect(() => {
        getContactInfo(id)
    },[]);
        function bufferToImage(buffer) {
        return `data:image/avif;base64,${ buffer}`;
    }

    const selectProfile = () => {
        inputRef.current.click();
    };

    const updateProfile = async (profile) => {
        try {
            if (!isNaN(contactId)) {
                await uploadImage(contactId, profile);
            } else {
                console.log('Invalid contact ID:', id);
            }
        } catch (error) {
            console.log(error.message);
        }
    }
    function handleChange(fieldName,event) {
        setContact(Object.assign({}, contact, { [fieldName]: event.target.value }));
    }
    const handleContactUpdate = async (event) => {
        event.preventDefault();

        await updateContact(contactId,contact.name,contact.phoneNumber,contact.email,contact.profile,
            contact.stillInContact,contact.companyName,contact.companyLocation,contact.jobTitle);
        await getContactInfo(id)}

    return (
        <>
            <Link to={"/"}>
                <i className="bi bi-arrow-left" style={{color: "whitesmoke"}}>Return to list</i>
            </Link>
            <div className="profile">
                <div className='profile__details'>
                    {contact.profile !== undefined && (
                        <img
                            src={bufferToImage(contact.profile)}
                            alt={`Profile Picture Of ${contact.name}`}
                        />
                    )}
                    <div className="profile__metadata">
                        <p className="profile__name">{contact.name}</p>
                        <button className="btn" onClick={selectProfile}><i className="bi bi-cloud-upload">Change
                            Profile</i>
                        </button>

                    </div>
                </div>
                <div className="profile__settings">  <div>
                    <form onSubmit={handleContactUpdate} className="form">
                        <div className='user-details'>

                        <div className="input-box">
                            <span className="details">Name</span>
                            <input type="text" value={contact.name} onChange={event =>
                                handleChange("name", event)} name='name' required/>
                        </div>
                        <div className="input-box">
                            <span className="details">Email</span>
                            <input type="text" value={contact.email} onChange={event =>
                                handleChange("email", event)} name='email' required/>
                        </div>
                        <div className="input-box">
                            <span className="details">Title</span>
                            <input type="text" value={contact.jobTitle} onChange={event =>
                                handleChange("jobTitle", event)} name='title' required/>
                        </div>
                        <div className="input-box">
                            <span className="details">Phone Number</span>
                            <input type="text" value={contact.phoneNumber.toString()} onChange={event =>
                                handleChange("phoneNumber", event)} name='phoneNumber'
                            />
                        </div>
                        <div className="input-box">
                            <span className="details">Company name</span>
                            <input type="text" value={contact.companyName} onChange={event =>
                                handleChange("companyName", event)} name='companyName'
                                   required/>
                        </div>
                        <div className="input-box">
                            <span className="details">Company address</span>
                            <input type="text" value={contact.companyLocation} onChange={event =>
                                handleChange("companyLocation", event)} name='companyLocation'
                            />
                        </div>
                        <div className="input-box">
                            <span className="details">Status</span>
                            <select className="bi-option" style={{background: "darkblue", color: "whitesmoke"}}
                                    name="status"
                                    value={contact.stillInContact}
                                    onChange={event => handleChange("stillInContact", event)}>
                                <option value="ACTIVE">ACTIVE</option>
                                <option value="IN_ACTIVE">IN_ACTIVE</option>
                            </select>
                        </div>
                        <div className="form_footer">
                            <button type="submit" className="btn">Save</button>
                        </div>
                        </div>

                    </form>
                </div>
                </div>
            </div>
            <form style={{opacity: 0}}>
                <input type="file" ref={inputRef} onChange={event =>
                    updateProfile(event.target.files[0])} name='file' accept='image/*'/>
            </form>

        </>
    )


}
export {ContactDetails};

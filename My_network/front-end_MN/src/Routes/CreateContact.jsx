import {useModel} from "../hooks/useModel.jsx";
import { useRef, useState} from "react";
import {createContact, getAllContact, uploadImage} from "../data/ApiEndPoint.js";
export const CreateContact = () => {
    const {toggleModel} = useModel();
    const fileRef = useRef();
    const [profile,setProfile] = useState(undefined)
    const [values,setValues] = useState({
    name : "",
    email : "",
    phoneNumber : "",
    companyLocation : "",
    companyName : "",
    jobTitle : "",
    profile : undefined,
    stillInContact : "ACTIVE"
    }
);

    async function handleNewContact(event) {
        event.preventDefault();
        try{
        const {data} = await createContact(values)

            await uploadImage(data.id,profile)
    console.log(data)
            await getAllContact();

            toggleModel(false)
            setProfile(undefined)
            fileRef.current.value = null;
            setValues({
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

        }catch (error){
            console.log(error.message)
        }
    }

    function handleChange(fieldName,event) {
        setValues(Object.assign({}, values, { [fieldName]: event.target.value }));
    }


    return (
        <>
            <div className="modal__header">
                <h3>New Contact</h3>
                <i onClick={() => toggleModel(false)} className="bi bi-x-lg"></i>
            </div>
            <div className="divider"/>
            <div className="modal__body">
                <form onSubmit={handleNewContact}>
                    <div className="user-details">
                        <div className="input-box">
                            <span className="details">Name</span>
                            <input type="text" value={values.name} onChange={event =>
                                handleChange("name", event)} name='name' required/>
                        </div>
                        <div className="input-box">
                            <span className="details">Email</span>
                            <input type="text" value={values.email} onChange={event =>
                                handleChange("email", event)} name='email' required/>
                        </div>
                        <div className="input-box">
                            <span className="details">Title</span>
                            <input type="text" value={values.jobTitle} onChange={event =>
                                handleChange("jobTitle", event)} name='title' required/>
                        </div>
                        <div className="input-box">
                            <span className="details">Phone Number</span>
                            <input type="text" value={values.phoneNumber.toString()} onChange={event =>
                                handleChange("phoneNumber", event)} name='phoneNumber'
                                   />
                        </div>
                        <div className="input-box">
                            <span className="details">Company name</span>
                            <input type="text" value={values.companyName} onChange={event =>
                                handleChange("companyName", event)} name='companyName'
                                   required/>
                        </div>
                        <div className="input-box">
                            <span className="details">Company address</span>
                            <input type="text" value={values.companyLocation} onChange={event =>
                                handleChange("companyLocation", event)} name='companyLocation'
                                   />
                        </div>
                        <div className="input-box">
                            <span className="details">Status</span>
                            <select className="bi-option" style={{background: "darkblue", color: "whitesmoke"}}
                                    name="status"
                                    value={values.stillInContact}
                                    onChange={event => handleChange("stillInContact", event)}>
                                <option value="ACTIVE">ACTIVE</option>
                                <option value="IN_ACTIVE">IN_ACTIVE</option>
                            </select>
                        </div>
                        <div className="file-input">
                        <span className="details">Profile Photo</span>
                            <input type="file" onChange={(event) => setProfile(event.target.files[0])}
                                  ref={fileRef} name='profile' accept='image/*' />
                        </div>
                    </div>
                    <div className="form_footer">
                        <button onClick={() => toggleModel(false)} type='button' className="btn btn-danger">Cancel
                        </button>
                        <button type='submit' className="btn">Save</button>
                    </div>
                </form>
            </div>
        </>
    )
}

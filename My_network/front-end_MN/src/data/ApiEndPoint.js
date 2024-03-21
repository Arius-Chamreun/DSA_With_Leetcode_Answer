import axios from "axios";

export const api = axios.create({
    baseURL:"http://localhost:8080/api/contacts"
})
export const createContact = async (contact) => {
    return await api.post("/create",contact)
}

export const getContact = async (id) => {

    return await api.get(`${id}`)
}

export const getAllContact = async (page= 0, size = 10) => {

    return await api.get(`/all?page=${page}&size=${size}`)
}

export const uploadImage = async (id,image) => {
    const formData = new FormData();
    formData.append("image",image)
    return await api.put(`/upload_image/${id}`,formData);
}

export const updateContact = async (id,name,phoneNumber,email,profile,stillInContact,
                                    companyName,companyLocation,jobTitle) => {

    const formData = new FormData();

    formData.append('name', name);
    formData.append('profile', profile);
    formData.append('email', email);
    formData.append('phoneNumber', phoneNumber);
    formData.append('stillInContact', stillInContact);
    formData.append('companyName', companyName);
    formData.append('companyLocation', companyLocation);
    formData.append('jobTitle', jobTitle);

    return await api.post(`/update/${id}`, formData)
}

export const deleteContact = async (name,id) => {

    return await api.delete(`/delete/${id}/${name}`)
}


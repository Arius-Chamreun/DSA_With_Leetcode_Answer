import {useEffect, useState} from 'react'
import {getAllContact} from "./data/ApiEndPoint.js";
import {Header} from "./component/Header.jsx";
import {Navigate, Route, Routes} from "react-router-dom";
import {ContactList} from "./Routes/ContactList.jsx";
import {useModel} from "./hooks/useModel.jsx";
import {CreateContact} from "./Routes/CreateContact.jsx";
import {ContactDetails} from "./Routes/ContactDetails.jsx";

function App() {
  const [data,setData] = useState({
    totalElements: undefined
  });
   const { modelRef,toggleModel} = useModel()
   const [currentPage,setCurrentPage] = useState(0);



  const getAllContacts =(async (page = 0, size = 10) => {
    try{
     setCurrentPage(page)
      const {data} = await getAllContact(page,size);
      setData(data)
    } catch (e){
      console.log(e);
    }
  })
    useEffect(() => {
        getAllContacts()
    },[]);
  const handleUpdateContact = () => {

  }


  return (
      <>
          <main className="main">
              <div className="container">
                  <Routes>
                      <Route path="/" element={<Navigate to="/contacts"/>}/>
                      <Route path="/contacts" element={
                          <>
                              <Header toggleModel={toggleModel} nOfContact={data.totalElements}/>
                              <ContactList data={data} currentPage={currentPage} fetchAllPage={getAllContacts}/>
                              <dialog ref={modelRef} className="modal" id="modal">
                                  <CreateContact/>
                              </dialog>
                          </>
                      }/>

                      <Route path="/contacts/:id" element={<ContactDetails updateContact={handleUpdateContact}/>}/>
                  </Routes>
              </div>
          </main>

      </>
  )
}

export default App

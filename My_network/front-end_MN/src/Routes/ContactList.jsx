import {Contact} from "./Contact.jsx";

export const ContactList = ({data,currentPage,fetchAllPage}) => {
    return (
        <main className="main">
            {data?.content?.length === 0 && <div>No Contact, Please add your contact</div>}
            <ul className="contact__list">
                {data?.content?.length > 0 && data.content.map(contact => <Contact contacts={contact} key={contact.id}/>)}
            </ul>
            {data?.content?.length > 0 && data?.totalPages > 1 &&
                <div className="pagination">
                    <a onClick={() => fetchAllPage(currentPage - 1)}
                       className={0 === currentPage ? "Disable" : ""}>&laguo;</a>

                    {
                        data && Array.from({ length: data.totalPages },
                            (page, index) => (
                                <a onClick={fetchAllPage(page)}
                                   className={currentPage === page ? "ACTIVE" : ""}
                                   key={page}>{page + 1}</a>
                            ))
                    }
                    <a onClick={() => fetchAllPage(currentPage + 1)}
                       className={data.totalPages === currentPage ? "Disable" : ""}>&raguo;</a>
                </div>}
        </main>
    )
}
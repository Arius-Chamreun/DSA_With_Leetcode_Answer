import {useEffect, useRef} from "react";

export const useModel =  () => {

    const modelRef = useRef();

useEffect(() => {
    // This useEffect is for the modelRef in the dialog tag, so that the modelRef is not undefined when called.
    modelRef.current = document.getElementById('modal');
}, []);

    const toggleModel = show => {
        if (modelRef.current) {
            if (show) {
                modelRef.current.showModal();
            } else {
                modelRef.current.close();
            }
        }
    };

    return{modelRef,toggleModel};
}
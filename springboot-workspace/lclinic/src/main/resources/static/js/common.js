onload = () => {
	console.log("%c Location Info", "color:orange; font-style:italic;background-color:green;");
	console.log(location);
	console.log("%c ---------------------", "color:orange; font-style:italic;background-color:green;");
	
	const menuEle = document.querySelectorAll(".radio-inputs .radio input");
		
	if (location.pathname == "/") {
		menuEle[0].checked = true;
	} else {
		menuEle[1].checked = true;
	}
}
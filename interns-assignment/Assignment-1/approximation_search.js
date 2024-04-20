let availableKeywords = [
    "HTML",
    "CSS",
    "Web development",
    "Web Design",
    "Approximation search",
    "Where to learn coding",
    "best practices",
    "how to create websites"
];
const search_box = document.querySelector(".search-box");
const result_box = document.querySelector(".result-box");
const input_box = document.querySelector("#input-box");

input_box.onkeyup = function(){
    let result = [];
    let input = input_box.value;
    if(input.length){
        result = availableKeywords.filter((keyword)=>{
           return keyword.toLowerCase().includes(input.toLowerCase());
        });
        console.log(result);
    }
    display(result);
    
    if(!result.length){
        result_box.innerHTML = "";
    }
}

function display(result){
    const content = result.map((list)=>{
        return "<li onclick=selectInput(this)>" + list + "</li>";
    });
    result_box.innerHTML = "<ul>" + content.join("") + "</ul>";
}
function selectInput(list){
    input_box.value = list.innerHTML;
    result_box.innerHTML ="";
}
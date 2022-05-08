// template generator for list elements
function template(strings, ...keys) {
    return (function(...values) {
      let dict = values[values.length - 1] || {};
      let result = [strings[0]];
      keys.forEach(function(key, i) {
        let value = Number.isInteger(key) ? values[key] : dict[key];
        result.push(value, strings[i + 1]);
      });
      return result.join('');
    });
}
// buttons
let buttons = `<button class="btn btn-sm" type="del"><img src="./assets/icons/bin.svg" alt="del"/></button>
                <button class="btn btn-sm" type="mod"><img src="./assets/icons/edit.svg" alt="mod"/></button>`;
// templates for List elements
let contactLiTemplate = template`<li class="list-group-item" type="contact" id="${0}">
                                                <span>${1}</span>
                                                <span>${2}</span> 
                                                <span>${3}</span> ${'buttons'}</li>`;
let addressLiTemplate = template`<li class="list-group-item" type="address" id="${0}">
                                                <span>${1}</span>, 
                                                <span>${2}</span>, 
                                                <span>${3}</span>, 
                                                <span>${4}</span>, 
                                                <span>${5}</span> ${'buttons'}</li>`;
let phoneLiTemplate = template`<li class="list-group-item" type="phone" id="${0}">
                                                <span>${1}</span>:
                                                (<span>${2}</span>) 
                                                <span>${3}</span> ${'buttons'}</li>`;
let dateLiTemplate = template`<li class="list-group-item" type="date" id="${0}">
                                                <span>${1}</span>: 
                                                <span>${2}</span> ${'buttons'}</li>`; 
let componentIds= {
    contact: ["fName", "mName", "lName"],
    address: ["addressType", "addressVal", "city", "state", "zip"],
    phone: ["phoneType", "areaCode", "number"],
    date: ["dateType", "dateVal"]
}
let forms = {
contact: `<form id="contact-form">
<div class="form-group">
    <input type="text" placeholder="First Name" class="form-control" id="fName"/>
</div>
<div class="form-group">
    <input type="text" placeholder="Middle Name" class="form-control" id="mName"/>
</div>
<div class="form-group">
    <input type="text" placeholder="Last Name" class="form-control" id="lName"/>
</div>
<button id="contact-submit" type="submit" class="btn btn-primary">Submit</button>
<button id="contact-cancel" class="btn btn-danger">Cancel</button>
</form>`,
address: `<form id="address-form">
<div class="form-group">
    <input type="text" placeholder="Address Type" class="form-control" id="addressType"/>
</div>
<div class="form-group">
    <textarea rows="2" placeholder="Address" class="form-control" id="addressVal"></textarea>
</div>
<div class="form-group">
    <input type="text" placeholder="City" class="form-control" id="city"/>
</div>
<div class="form-group">
    <input type="text" placeholder="State" class="form-control" id="state"/>
</div>
<div class="form-group">
    <input type="text" placeholder="Zip code" class="form-control" id="zip"/>
</div>
<button id="address-submit" type="submit" class="btn btn-primary">Submit</button>
<button id="address-cancel" class="btn btn-danger">Cancel</button>
</form>`,
phone: `<form id="phone-form">
<div class="form-group">
    <input type="text" placeholder="Phone Type" class="form-control" id="phoneType"/>
</div>
<div class="form-group">
    <input type="text" placeholder="Area code" class="form-control" id="areaCode"/>
</div>
<div class="form-group">
    <input type="text" placeholder="Number" class="form-control" id="number"/>
</div>
<button id="phone-submit" type="submit" class="btn btn-primary">Submit</button>
<button id="phone-cancel" class="btn btn-danger">Cancel</button>
</form>`,
date: `<form id="date-form">
<div class="form-group">
    <input type="text" placeholder="Date Type" class="form-control" id="dateType"/>
</div>
<div class="form-group">
    <input type="date" value="2000-01-31" class="form-control" id="dateVal"/>
</div>
<button id="date-submit" type="submit" class="btn btn-primary">Submit</button>
<button id="date-cancel" class="btn btn-danger">Cancel</button>
</form>`};
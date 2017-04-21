var exec = require('cordova/exec'),
    cordova = require('cordova');


function Overlay() {
    this.createOverlay = function(html) {
		console.log(html);
	}
}

module.exports = new Overlay();

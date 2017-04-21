var exec = require('cordova/exec'),
    cordova = require('cordova');


function Overlay() {
    this.createOverlay = function(html) {
		exec(function(res) {console.log(res);}, function(res) {console.log(res);}, "Overlay", "createOverlay", [html]);
	}
}

module.exports = new Overlay();

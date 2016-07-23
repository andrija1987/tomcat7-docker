var jq = jQuery.noConflict();
var glbZaposleniId = 0;

jq(function() {
	jq("#oknoKomentar").dialog( 
			{
				dialogClass: "oknoKomentar",
				show: { effect: "blind", duration: 500 },
				hide: { effect: "explode", duration: 1000 },
				modal: true, 
				autoOpen: false, 
				width: 400, 
				buttons:
				{
					"save":{
						text:'Shrani',
						className:'save',
						click: shraniKomentar	
					},
					"cancel":{
						text:'Prekliči',
						className:'cancel',
						click: prekiniVnosKomentarja
					}
				}
			} 
	);
});

//-------------------------------Start - Uroš 

jq(function() {
	jq("#oknoUrejanjeDopusta").dialog( 
			{
				dialogClass: "urejanjeDopusta",
				show: { effect: "blind", duration: 500 },
				hide: { effect: "explode", duration: 1000 },
				modal: true, 
				autoOpen: false, 
				width: 400, 
				buttons:
				{
					"save":{
						text:'Shrani',
						className:'save',
						click: shraniDopust
					},
					"cancel":{
						text:'Prekliči',
						className:'cancel',
						click: zapriOkno
					}
				}
			} 
	);
});

function upravljanjeDopusta(zaposleniId, priimek, ime, stDniDopusta, leto) {
	console.log("upravljanjeDopusta(zaposleniId) :" + zaposleniId + ", leto: " + leto);
	
	glbZaposleniId = zaposleniId;
		
	jq("#ui-dialog-title-oknoUrejanjeDopusta").html("Urejanje dopusta " + leto);
	
	jq("#oknoUrejanjeDopusta").data('leto', leto).dialog('open');
//	jq("#oknoUrejanjeDopusta").dialog('open');
	
	jq.post("/bugitracker/admin/urejanjeDopusta", { zaposleniId: zaposleniId, leto: leto}, 
			function(data) {
				console.log(data);
				data = data.replace("zaposleni.priimek", priimek+"");
				data = data.replace("zaposleni.ime", ime+"");
//				data = data.replace(-1, 0);
				
				jq("#oknoUrejanjeDopusta").empty();
				jq("#oknoUrejanjeDopusta").append(data);
			}
		);
}

function trenutnoVnasanjeParametrov() {
	console.log("trenutnoVnasanjeParametrov()");
	
	//var datum = new Date();
	
	var trenutniDatum = new Date();
	var datumZapadlostiPrenesenegaDopusta = new Date(trenutniDatum.getFullYear(), 6, 1);
	
	var preneseni = document.getElementById("preneseni_dopust").value;
	var redni = document.getElementById("redni_dopust").value;
	var izkorisceni = document.getElementById("izkorisceni_dopust").value;
	var zapadelDopust = document.getElementById("zapadel_dopust").value;
	var neizkorisceni = document.getElementById("neizkorisceni_dopust").value;
	
	if (trenutniDatum > datumZapadlostiPrenesenegaDopusta) {
		
//		zapadelDopust = parseInt(preneseni) - parseInt(izkorisceni);
//		
//		var tempNeizkoriscen = parseInt(neizkorisceni);
//		
//		if (zapadelDopust > 0) {
//			//tempNeizkoriscen = parseInt(neizkorisceni) - (parseInt(preneseni) - parseInt(izkorisceni));
//			console.log(parseInt(neizkorisceni));
//			console.log(parseInt(preneseni) - parseInt(izkorisceni));
//		} else {
//			console.log(parseInt(neizkorisceni));
//			console.log(parseInt(preneseni) - parseInt(izkorisceni));
//		}
//		
		document.getElementById("neizkorisceni_dopust").value = parseInt(neizkorisceni);
		
	} else {
		document.getElementById("neizkorisceni_dopust").value = parseInt(preneseni) + parseInt(redni) - parseInt(izkorisceni);
	}
}

function shraniDopust(leto){
	console.log("shraniDopust()");
	
	var date = new Date();
//	var leto = date.getFullYear();
	
	var leto = jq("#oknoUrejanjeDopusta").data('leto');
	console.log(leto);
	
	var preneseni = document.getElementById("preneseni_dopust").value;
	var redni = document.getElementById("redni_dopust").value;
	var neizkorisceni = document.getElementById("neizkorisceni_dopust").value;
	var izkorisceni = document.getElementById("izkorisceni_dopust").value;
	var zapadelDopust = document.getElementById("zapadel_dopust").value;
	
	jq.post("/bugitracker/admin/shraniDopust", { zaposleniId: glbZaposleniId, preneseni: preneseni, redni: redni, neizkorisceni: neizkorisceni, izkorisceni: izkorisceni, leto: leto, zapadelDopust: zapadelDopust},
		function(data) {
			console.log("uspesno shranjeno: " + data);
	});
	
	zapriOkno();
}

function zapriOkno() {
	console.log("zapriOkno()");
	
	jq("#oknoUrejanjeDopusta").dialog("close");
}

//-------------------------------End - Uroš 

var timer;
var firing = false;
var firingFunc = pobarvaj;

//Metoda zazna enojni oz. dvojni klik uporabnika.
//Blesavo
function azurirajOdsotnost(li, zaposleniId, dan) {
  // Detect the 2nd single click event, so we can stop it
	if(firing){
		firingFunc = prikaziOkno; 
		return;
    }

    firing = true;
    timer = setTimeout(function() { 
    	firingFunc(li, zaposleniId, dan); 

       // Always revert back to singleClick firing function
    	firingFunc = pobarvaj;
    	firing = false;
    }, 	250);
}

function pobarvaj(li, zaposleniId, dan) {
	var barva = getBarva();
	var razlogId = getRazlog();
	if (razlogId != -2) {
		jq.post("/bugitracker/admin/azurirajOdsotnost", { zaposleniId: zaposleniId, razlogId: razlogId, dan: dan }, 
		    function(data){
				li.style.backgroundColor = barva;
				window.location.reload();
			}
		);
	}
}

function prikaziOkno(li, zaposleniId, dan) {
	var razlogId = getRazlog();
	var barva = getBarva();
	console.log(zaposleniId + ", " + razlogId + ", " + dan);
	if (razlogId != -1) {
		li.style.backgroundColor = barva;
		jq("#oknoKomentar").dialog('open');
		jq.post("/bugitracker/admin/novKomentar", { zaposleniId: zaposleniId, razlogId: razlogId, dan: dan }, 
			function(data) {
				jq("#oknoKomentar").empty();
				jq("#oknoKomentar").append(data);
			}
		);
	}
}


function prekiniVnosKomentarja() {
	jq("#oknoKomentar").dialog("close");
}

function shraniKomentar() {
	var razlogId = getRazlog();
	var komentar = document.getElementById("komentarText").value;
	jq.post("/bugitracker/admin/shraniKomentar", { razlogId: razlogId, komentar: komentar },  
		function(data) {
			prekiniVnosKomentarja();
			window.location.reload();
		}
	);
}

function getRazlog() {
	var razlogi = document.getElementsByName("razlogi");
	for (var i = 0; i < razlogi.length; i++) {
		if (razlogi[i].checked == true) {
			return razlogi[i].value;
		} 
	}
	return "-2";
}

function getBarva() {
	var razlogi = document.getElementsByName("razlogi");
	for (var i = 0; i < razlogi.length; i++) {
		if (razlogi[i].checked == true) {
			return rgb2hex(document.getElementById("div_barva_"+razlogi[i].value).style.backgroundColor);
		}
	}
	return "#2191c0";
}

var hexDigits = new Array("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"); 

//Function to convert hex format to a rgb color
function rgb2hex(rgb) {
	rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
	return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
}

function hex(x) {
	return isNaN(x) ? "00" : hexDigits[(x - x % 16) / 16] + hexDigits[x % 16];
}

App.onLaunch = function(options) {
    var showcase = createShowcase();
    navigationDocument.pushDocument(showcase);
}

var createShowcase = function() {
    var showcaseString = `<?xml version="1.0" encoding="UTF-8"?><document><showcaseTemplate><img src="https://radio.nrk.no/Pakke74/3.0.163.0a/content/images/plug-bg-gradient.png"/><banner><title>Aktuelt</title><row><button><text>Slideshow</text></button><button><text>Screensaver</text></button></row></banner><carousel><section><lockup><img src="https://gfx.nrk.no/s7VSdo-vuHWR2G2sRqFqHwCTUsHLyFtqTtLWUDkb1qhg" width="439" height="246"/><description>- Du ser ut som et hamster pￃﾥ bildet, Siri!</description></lockup><lockup><img src="https://gfx.nrk.no/H-17EBBqymQk2wKqqTOwPw8RZSopLh9U4R7eNMQRKolg" width="439" height="246"/><description>- Jeg mￃﾥ bruke antibac hele tiden.</description></lockup><lockup><img src="https://gfx.nrk.no/La-x1E0X_HAzzZrr21uIfwXFU4Kv3jEeNAVu9DCssikw" width="439" height="246"/><description>Hva skjer hvis en robot ansetter?</description></lockup><lockup><img src="https://gfx.nrk.no/mC5sMxreT0aO7VTx5m-zfggyjFJfWEpLdQdJhLAT16AA" width="439" height="246"/><description>Er Elvis virkelig dￃﾸd?</description></lockup><lockup><img src="https://gfx.nrk.no/acv3xW0HNeMQYCq-RHIJngDm18psgjrr8OXqH1CrV83g" width="439" height="246"/><description>- Jeg har ￃﾥpnet et nytt kapittel i livet</description></lockup><lockup><img src="https://gfx.nrk.no/E1Rc8QA-Q5dFEAm-PDgzpAIrESs-m6b4eMNgBC5sNOrQ" width="439" height="246"/><description>Ny innsamlingsrekord for ￃﾥrets P3aksjon!</description></lockup></section></carousel></showcaseTemplate></document>`;
    
    var parser = new DOMParser();
    
    var showcaseDoc = parser.parseFromString(showcaseString, "application/xml");
    
    return showcaseDoc
}

App.onWillResignActive = function() {
    
}

App.onDidEnterBackground = function() {
    
}

App.onWillEnterForeground = function() {
    
}

App.onDidBecomeActive = function() {
    
}

App.onWillTerminate = function() {
    
}


/**
 * This convenience funnction returns an alert template, which can be used to present errors to the user.
 */
var createAlert = function(title, description) {
    
    var alertString = `<?xml version="1.0" encoding="UTF-8" ?>
    <document>
    <alertTemplate>
    <title>${title}</title>
    <description>${description}</description>
    </alertTemplate>
    </document>`
    
    var parser = new DOMParser();
    
    var alertDoc = parser.parseFromString(alertString, "application/xml");
    
    return alertDoc
}


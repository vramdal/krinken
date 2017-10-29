App.onLaunch = function(options) {
    var showcase = createShowcase();
    navigationDocument.pushDocument(showcase);
}

var createShowcase = function() {
    var showcaseString = `<!-- INSERT SCRIPT HERE -->`;

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

(function (out) {
    var zcls = this.getZclass(), uuid = this.uuid;

//    <div class="z-quickfilter">
//        <span class="z-quickfilter-text">Všechno možné i nemožné:</span>
//        <span class="z-quickfilter-list"></span>
//        <input class="z-quickfilter-real" type="text" value="" />
//        <span class="z-quickfilter-del"></span>
//        <span class="z-quickfilter-loupe"></span>
//    </div>
    out.push('<div', this.domAttrs_(), '>');
    if ( this.labelVisible ) {
        out.push('<span id="', uuid, '-text" class="', zcls, '-text', '">', this.getLabel(), '</span>');
        if (this.getQuickFilterPopupSize() > 1) {
            out.push('<span id="', uuid, '-list" class="', zcls, '-list', '"></span>');
        }
    }
    out.push('<input placeholder="', this._placeholder, '" id="', uuid, '-real" class="', zcls, '-real" type="text" value="',this.getValue(),'"/>');
    out.push('<span id="', uuid, '-del" class="', zcls, '-del', '"></span>');
    
//    if (this.getQuickFilterButton()) {
//        
//        $class = "-button z-button-os";
//        $text = this.getQuickFilterButton();
//    }
//    else {
//        $class = "-magnifier";
//        $text = "";
//    }
    if (this.getQuickFilterButton()) {
        
        $class = "-button ";
        if (this.getQuickFilterButtonClass()) {
            $class += this.getQuickFilterButtonClass();
        }
        $text = this.getQuickFilterButton();
        out.push('<button type="button" id="', uuid, '-magnifier" class="', zcls, $class, '">' + $text + '</button>');
    }
    else {
        out.push('<span id="', uuid, '-magnifier" class="', zcls, '-magnifier', '"></span>');
    }
    
    out.push('<br class="z-dlzklib-clear"/>');
    out.push('</div>');
})
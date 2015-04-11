def numeral(arabic, r=''):
    _t = {1000:'M', 900:'CM', 500:'D', 400:'CD', 100:'C', 90:'XC',
            50:'L', 40:'XL', 10:'X', 9:'IX', 5:'V', 4:'IV', 1:'I'}
    if arabic == 0:
        return(r)
    for n in reversed(sorted(_t.keys())):
        if n <= arabic:
            r += _t[n]
            return(numeral(arabic-n, r))
            break

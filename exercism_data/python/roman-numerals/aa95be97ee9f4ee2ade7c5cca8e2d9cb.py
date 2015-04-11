def failed_numeral(arabic, r=''):
    _t = {1000:'M', 500:'D', 100:'C', 50:'L', 10:'X', 5:'V', 1:'I'}
    if arabic == 0:
        return(r)
    for n in reversed(sorted(_t.keys())):
        if n <= arabic:
            r += _t[n]
            return(numeral(arabic-n, r))
            break

def numeral(arabic):
    r = ''
    if arabic == 0:
        return(r)
    l = list(str(arabic))
    try:
        thousands = int(l[-4])
    except:
        thousands = 0
    try:
        hundreds = int(l[-3])
    except:
        hundreds = 0
    try:
        tens = int(l[-2])
    except:
        tens = 0
    ones = int(l[-1])
    r += 'M'*thousands
    if hundreds > 4 and hundreds != 9:
        r += 'D' + (hundreds-5)*'C'
    elif hundreds < 5 and hundreds != 4:
        r += 'C'*hundreds
    elif hundreds == 9:
        r += 'CM'
    elif hundreds == 4:
        r += 'CD'
    if tens > 4 and tens != 9:
        r += 'L' + (tens-5)*'X'
    elif tens < 5 and tens != 4:
        r += 'X'*tens
    elif tens == 9:
        r += 'XC'
    elif tens == 4:
        r += 'XL'
    if ones > 4 and ones != 9:
        r += 'V' + (ones-5)*'I'
    elif ones < 5 and ones != 4:
        r += 'I'*ones
    elif ones == 9:
        r += 'IX'
    elif ones == 4:
        r += 'IV'
    return(r)

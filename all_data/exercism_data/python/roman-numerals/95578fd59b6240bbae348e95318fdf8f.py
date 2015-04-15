def numeral(num):
    char = 'IVXLCDM  '
    value = [1,5,10,50,100,500,1000]
    roman = ''

    for i in (6,4,2,0):
        roman += digit(num/value[i], char[i:i+3])
        num %= value[i]

    return roman

def digit(d, ch):
    dstr = ''

    if d == 9:
        dstr += ch[0] + ch[2]
        d = 0
    elif d >= 5:
        dstr += ch[1]
        d -= 5

    if d == 4:
        dstr += ch[:2]
    elif d:
        dstr += ch[0] * d

    return dstr

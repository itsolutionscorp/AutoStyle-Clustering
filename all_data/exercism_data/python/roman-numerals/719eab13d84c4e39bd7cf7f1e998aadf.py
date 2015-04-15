pairs = [
    (1000,'M'),
    (900,'CM'),
    (500,'D'),
    (400,'CD'),
    (100,'C'),
    (90,'XC'),
    (50,'L'),
    (40,'XL'),
    (10,'X'),
    (9,'IX'),
    (5,'V'),
    (4,'IV'),
    (1,'I')
]
def numeral(arabic):
    numeralStr = ''
    for pair in pairs:
        while arabic >= pair[0]:
            numeralStr += pair[1]
            arabic -= pair[0]
    return numeralStr

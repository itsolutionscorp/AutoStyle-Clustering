def numeral(arabic):
    trans = [(1000, 'M'), (900, 'CM'), (500, 'D'), (400, 'CD'), (100, 'C'), (90, 'XC'), (50, 'L'), (40, 'XL'), (10, 'X'), (9, 'IX'), (5, 'V'), (4, 'IV'), (1, 'I')]
    roman = []
    arab = arabic + 1
    for k in trans:
        while arab > k[0]:
            arab -= k[0]
            roman.append(k[1])
    return ''.join(roman)

romans = [(1000, 'M'), (900, 'CM'), (500, 'D'), (400, 'CD'), (100, 'C'), (90, 'XC'), (50, 'L'), (40, 'XL'), (10, 'X'),
        (9, 'IX'), (5, 'V'), (4, 'IV'), (1, 'I')]

def numeral(arabic_num):
    ret_str = ''
    while arabic_num > 0:
        for r in romans:
            if arabic_num >= r[0]:
                arabic_num -= r[0]
                ret_str += r[1]
                break

    return ret_str

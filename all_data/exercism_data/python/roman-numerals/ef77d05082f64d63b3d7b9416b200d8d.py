__author__ = 'Hinek'

from collections import OrderedDict

numeral_map = OrderedDict([(1000, 'M'), (500, 'D'), (100, 'C'), (50, 'L'), (10, 'X'), (5, 'V'), (1, 'I')])

def numeral(number):
    result = ''
    for i in xrange(7):
        divisor = numeral_map.items()[i][0]
        multiplier = number / divisor
        if multiplier > 0:
            if multiplier > 3 and i > 1:
                n = 1
                if len(result) > 0 and result[-1] == numeral_map.items()[i-1][1]:
                    result = result[:-1]
                    n += 1
                result += numeral_map.items()[i][1] + numeral_map.items()[i-n][1]
            else:
                result += numeral_map.items()[i][1] * multiplier
            number -= multiplier * divisor
    return result

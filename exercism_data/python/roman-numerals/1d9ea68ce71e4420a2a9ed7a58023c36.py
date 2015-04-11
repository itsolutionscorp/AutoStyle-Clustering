from collections import OrderedDict

_digit_map = OrderedDict([(1000, 'M'),
                          (900, 'CM'),
                          (500, 'D'),
                          (400, 'CD'),
                          (100, 'C'),
                          (90, 'XC'),
                          (50, 'L'),
                          (40, 'XL'),
                          (10, 'X'),
                          (9, 'IX'),
                          (5, 'V'),
                          (4, 'IV'),
                          (1, 'I')]);

def numeral(num):
    xs = []
    for k, v in _digit_map.items():
        while num >= k:
            xs.append(v)
            num -= k
    return ''.join(xs)

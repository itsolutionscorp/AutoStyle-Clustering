import collections

roman_numerals = collections.OrderedDict([(1000, "M"),
                                          (900, "CM"),
                                          (500, "D"),
                                          (400, "CD"),
                                          (100, "C"),
                                          (90, "XC"),
                                          (50, "L"),
                                          (40, "XL"),
                                          (10, "X"),
                                          (9, "IX"),
                                          (5, "V"),
                                          (4, "IV"),
                                          (1, "I")])


def numeral(n):
    s = ''
    for k in roman_numerals:
        while n >= k:
            s += roman_numerals[k]
            n -= k
    return s

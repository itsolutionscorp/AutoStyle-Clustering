romannums =  [ (1000, "M"), (900, "CM"), (500, "D"), (400, "CD") ,
                (100, "C"),  (90, "XC"),  (50, "L"),  (40, "XL") ,
                 (10, "X"),   (9, "IX"),   (5, "V"),   (4, "IV") ,
                  (1, "I") ]

def numeral(number):
    romanstr = ""
    testidx = 0
    while number > 0:
        (arabic, romanchr) = romannums[testidx]
        if number >= arabic:
            romanstr += romanchr
            number -= arabic
        else:
            testidx += 1
    return romanstr

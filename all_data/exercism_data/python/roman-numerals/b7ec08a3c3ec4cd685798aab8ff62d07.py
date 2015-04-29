def numeral( N ):
    digits = [ (1000,'M'),
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
               (1,'I') ]

    roman_numeral = ''
    for d in digits:
        while d[0] <= N:
            roman_numeral += d[1]
            N -= d[0]
    return roman_numeral

def numeral(arabic):    
    roman = ''
    remaining = arabic
    values  = (1000, 900,  500, 400,  100, 90,   50,  40,   10,  9,    5,   4,    1 )
    symbols = ("M",  "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" )
    
    for i in range(len(values)):
        while remaining >= values[i]:
            roman += symbols[i]
            remaining -= values[i]        
    return roman

def roman_digit( d, one, five, ten ):
    return { 0:'',
             1:one,
             2:one+one,
             3:one+one+one,
             4:one+five,
             5:five,
             6:five+one,
             7:five+one+one,
             8:five+one+one+one,
             9:one+ten }[ d ]

def roman_ones( d ):
    return roman_digit( d, 'I', 'V', 'X' )

def roman_tens( d ):
    return roman_digit( d, 'X', 'L', 'C' )

def roman_hundreds( d ):
    return roman_digit( d, 'C', 'D', 'M' )

def roman( N ):

    return 'M' * int(N/1000) + \
         roman_hundreds( int(N/100) % 10 ) + \
         roman_tens( int(N/10) % 10 ) + \
         roman_ones( int(N) % 10 )


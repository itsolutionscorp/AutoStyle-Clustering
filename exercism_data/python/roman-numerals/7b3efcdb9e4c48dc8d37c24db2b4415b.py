numerals = {1000: 'M', 500: 'D', 100: 'C', 50: 'L', 10: 'X', 5: 'V', 1: 'I'}

def numeral(arabic):
    roman = ''
    
    # Set current to max numeral M for 1000
    max = 1000
    
    ninetieth   = lambda x: x * 9.0 / 10.0
    fortieth    = lambda x: x * 4.0 / 10.0
    half        = lambda x: x / 2.0
    tenth       = lambda x: x / 10.0
    
    # Run until all numerals have been found
    while arabic > 0:
        
        # Find number of current max numeral and append (1xxx,1xx,1x,1)
        while arabic >= max:
            roman += numerals[int(max)]
            arabic -= max

        # Find values between 9/10x and x (9xx,9x,9)
        if arabic >= ninetieth(max):
            roman += numerals[int(tenth(max))] + numerals[int(max)]
            arabic -= ninetieth(max)
        
        # Find values between 5/10x and 9/10x (500-899, 50-89, 5-8)
        if arabic >= half(max):
            roman += numerals[int(half(max))]
            arabic -= half(max)
            
        # Find values between 4/10x and 5/10x (4xx,4x,4)
        if arabic >= fortieth(max):
            roman += numerals[int(tenth(max))] + numerals[int(half(max))]
            arabic -= fortieth(max)
        
        max = max / 10
    
    return roman 

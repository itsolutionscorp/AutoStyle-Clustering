numeral_trans = [(1000, 'M'),
                 (500, 'D'),
                 (100, 'C'),
                 (50, 'L'),
                 (10, 'X'),
                 (5, 'V'),
                 (1, 'I'),]
                 
less_numeral = numeral_trans[2::2]
#possible preceding numerals

def numeral(num):
    return numeral_help(num, '')

def numeral_help(num, roman_str):
    
    if num == 0:
        return roman_str
    #base case
    
    for i, (factor, roman) in enumerate(numeral_trans):

        if num >= factor:
            return numeral_help(num - factor, roman_str+roman)
            
        less_factor, less_roman = next(((f, r) for f, r in less_numeral if factor > f), None)
        
        if less_factor and num >= (factor - less_factor):
            return numeral_help(num-factor+less_factor, roman_str+less_roman+roman)
        #handles 4's and 9's cases    
        
        

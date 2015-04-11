def number(s):
    """ Takes in one number in grid representation form."""

    # Checks the input
    if len(s) != 4 or sum([len(x) for x in s]) != 3*len(s):
        raise ValueError("Input is not formed correctly.")

    # Check and return for a valid mapping, if none returns '?'
    for key in definitions:
        if definitions[key] == s:
            return key
    return '?'

def numbers(s):
    """ Takes in several numbers in grid representation form."""
    
    ans = ''
    
    # Splits the input grid into individual numbers and checks
    # using the 'number' function
    for i in range(len(s[0]) / 3):
        ans += number([x[i*3:i*3+3] for x in s])
        
    return ans
        

def grid(s):
    """ Handles several numbers too"""
    
    ans = ['' for i in range(4)]
    
    for each in s:
        for i in range(4):
            
            if each in definitions:
                ans[i] += definitions[each][i]
                
            else:
                ans[i] += definitions['?'][i]
                
    return ans

# Our "Library" of grid values and mappings
ZERO = [" _ ","| |","|_|","   "]
ONE = ["   ","  |","  |","   "]
TWO = [' _ ',' _|','|_ ','   ']
THREE = [' _ ',' _|',' _|','   ']
FOUR = ['   ','|_|','  |','   ']
FIVE = [' _ ','|_ ',' _|','   ']
SIX = [' _ ','|_ ','|_|','   ']
SEVEN = [' _ ','  |','  |','   ']
EIGHT = [' _ ','|_|','|_|','   ']
NINE = [' _ ','|_|',' _|','   ']
Q = [' _ ',"' /",' . ','   ',]

definitions = {'0':ZERO,
               '1':ONE,
               '2':TWO,
               '3':THREE,
               '4':FOUR,
               '5':FIVE,
               '6':SIX,
               '7':SEVEN,
               '8':EIGHT,
               '9':NINE,
               '?':Q}

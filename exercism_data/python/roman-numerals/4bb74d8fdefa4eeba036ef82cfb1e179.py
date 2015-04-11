roman_values= { 1: 'I', 4: 'IV', 5:'V', 9:'IX', 10:'X', 40:'XL', 50:'L',
                90:'XC', 100:'C', 400:'CD', 500:'D', 900:'CM', 1000:'M'}

def numeral(arabic):
    """Converts an arabic integer to a roman numeral string"""
    roman = ''
    while (arabic != 0):
        #Find the highest value that applies to the arabic number
        breakpoint = max([item for item in roman_values.keys()
                          if item <= arabic])
        #Apply the letter of that value and subtract from arabic number
        roman += roman_values[breakpoint]
        arabic -= breakpoint
    return roman

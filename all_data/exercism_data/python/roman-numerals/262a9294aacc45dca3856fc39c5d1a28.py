pos = [['I', 'V', 'X'], ['X', 'L', 'C'], ['C', 'D', 'M']]

def roman_digit(number, position):
    """ converts a digit in a number (in the units, tens
    or hundreds position) into its corresponding roman numberal
    """
    if number == 0:
        return ''
    elif number < 4:
        return position[0] * number
    elif number == 4:
        return position[0] + position[1]
    elif number < 9:
        return position[1] + position[0] * (number - 5)
    elif number == 9:
        return position[0] + position[2]
        
def numeral(number):
    roman_string = ""
    digit = 0 # Records if we are looking at units, tens or hundreds
    while number > 0 and digit < 3:
        roman_string = roman_digit(number % 10, pos[digit]) + roman_string
        number = number // 10
        digit += 1
        
    # Add on the appropriate number of thousands
    roman_string = 'M' * number + roman_string
    
    return roman_string

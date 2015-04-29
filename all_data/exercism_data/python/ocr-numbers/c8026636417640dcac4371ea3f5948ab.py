import string

default_digits = [' _     _  _     _  _  _  _  _ ', \
                  '| |  | _| _||_||_ |_   ||_||_|', \
                  '|_|  ||_  _|  | _||_|  ||_| _|', \
                  '                              '  ]
def build_digit_list(digits, rows, columns):
    """Iterate over digits using the character width as the skip
        and adding combining all rows for a character into a list.
        This can be used for digits of any size if properly formatted.
    """
    numbers = []
    digitsN = len(digits[0])
    for col in range(0,digitsN,columns):
        num = []
        for row in range(rows):
            num.append(digits[row][col:col+columns])
        numbers.append(num)
    return numbers

def number(digit, rowN = 4, columnN = 3, digits = default_digits):
    """Build list of OCR numbers then check if argument exists in list
    """
    if not all([len(row) == columnN for row in digit]) or \
        not len(digit) == rowN:
        raise ValueError('Invalid row or column size.')
        
    if not all([char in ' _|' for line in digit for char in line]):
        return '?'
        
    numbers = build_digit_list(default_digits, rowN, columnN)
    return str(numbers.index(digit)) if digit in numbers else '?'
    
def grid(digit, rowN = 4, columnN = 3, digits = default_digits):
    """Build list of OCR numbers then return the correct number
        using the argument as the index value 
    """
    if digit in string.digits:
        return build_digit_list(digits, rowN, columnN)[int(digit)]
    else:
        raise ValueError('Invalid digit.')

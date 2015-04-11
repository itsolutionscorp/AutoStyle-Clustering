arabic_roman_map = {1    : 'I',
                    5    : 'V',
                    10   : 'X',
                    50   : 'L',
                    100  : 'C',
                    500  : 'D',
                    1000 : 'M'}

numbers = [500, 100, 50, 10, 5, 1]
#numbers = [1, 5, 10, 50, 100, 500, 1000]

def numeral(arabic):
    result = ''
    # do thousands special case (no 4k, no need for special processing)
    while arabic > 1000:
        result += 'M'
        arabic -= 1000
    # do remaining
    for number in numbers:
        while arabic >= number:
            if arabic == number:
                result += arabic_roman_map[number]
                arabic -= number
            elif (arabic + number) == previous_number:
                result += arabic_roman_map[number]
                result += arabic_roman_map[previous_number]
                arabic -= (previous_number - number)
                break
            else:
                result += arabic_roman_map[number]      
                arabic -= number
        previous_number = number
    return result
            
        

            
    return result

def get_thousands(arabic):    
    result = ''
    while arabic > 1000:
        result += 'M'
        arabic -= 1000
    return result

def get_hundreds(arabic):
    result = ''
    while arabic > 100:
        if arabic > 500:
            result += 'D'
            arabic -= 500
        elif (arabic + 100) == 50: 
            result += 'C'
            result += 'D'
            break
        else: #less than 500
            result += 'C'
            arabic -= 100
    return result

def get_tens(arabic):
    result = ''
    while arabic > 10:
        if arabic > 50:
            result += 'L'
            arabic -= 50
        elif (arabic + 10) == 50: #less than 50
            result += 'X'
            result += 'L'
            break
        else:
            result += 'X'
            arabic -= 10
    return result
    
def get_ones(arabic):
    result = ''
    while arabic > 1:
        if arabic > 5:
            result += 'V'
            arabic -= 5
        elif (arabic + 1) == 5: #less than 50
            result += 'I'
            result += 'V'
            break
        else:
            result += 'I'
            arabic -= 1
    return result

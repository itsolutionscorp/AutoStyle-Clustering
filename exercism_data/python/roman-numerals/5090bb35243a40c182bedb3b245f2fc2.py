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

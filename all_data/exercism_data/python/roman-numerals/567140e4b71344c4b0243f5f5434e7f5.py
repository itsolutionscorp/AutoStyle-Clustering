def numeral(arabic_number):          
    roman_numerals = [('M', 1000), ('CM',900), ('D', 500), ('CD', 400), ('C', 100), ('XC', 90), ('L', 50), ('XL', 40), ('X', 10), ('IX',9 ), ('V', 5), ('IV', 4), ('I', 1)]
    result = ''
    number = arabic_number
    for roman_digit, arabic_value in roman_numerals:
        while arabic_value <= number:
            result += roman_digit
            number -= arabic_value
    return result

def numeral(arabic):
    roman_numerals = {1: 'I', 5: 'V', 10: 'X', 50: 'L', 100: 'C', 500: 'D', 1000: 'M'}
    roman=''        
    for i in sorted(roman_numerals)[::-1]:
        roman += divmod(arabic, i)[0]*roman_numerals[i]
        arabic -= divmod(arabic, i)[0]*i
    roman = roman.replace('VIIII', 'IX')
    roman = roman.replace('IIII', 'IV')
    roman = roman.replace('LXXXX', 'XC')
    roman = roman.replace('XXXX', 'XL')
    roman = roman.replace('DCCCC', 'CM')
    roman = roman.replace('CCCC', 'CD')
    return roman

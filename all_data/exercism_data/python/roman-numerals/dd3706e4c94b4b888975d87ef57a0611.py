numerals = {1000: 'M', 500: 'D', 100: 'C', 50: 'L', 10: 'X', 5: 'V', 1: 'I', \
            900: 'CM', 400: 'CD', 90: 'XC', 40: 'XL', 9: 'IX', 4: 'IV'}

def numeral(arabic):
    roman = ''

    for num in sorted(numerals)[::-1]:

        quotient  =  arabic // num
        arabic    =  arabic % num
        
        if quotient:
            roman += quotient * numerals[num]
            
    return roman

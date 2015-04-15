ARABIC_ROMAN = {
    1:"I", 2:"II", 3:"III", 4:"IV", 5:"V", 6:"VI", 7:"VII", 8:"VIII", 9:"IX", 
    10:"X", 20:"XX", 30:"XXX", 40:"XL", 50:"L", 60:"LX", 70:"LXX", 80:"LXXX", 90:"XC",
    100:"C", 200:"CC", 300:"CCC", 400:"CD", 500:"D", 600:"DC", 700:"DCC", 800:"DCCC", 900:"CM", 
    1000: "M", 2000:"MM", 3000:"MMM",
}

def numeral(arabic_number):
    roman_singles = ""
    roman_tens = ""
    roman_hundreds = ""
    roman_thousands = ""
    if arabic_number % 10 != 0:
        singles = arabic_number % 10
        roman_singles = ARABIC_ROMAN[singles]
        arabic_number -= arabic_number % 10
    if arabic_number % 100 != 0:
        tens = arabic_number % 100
        roman_tens = ARABIC_ROMAN[tens]
        arabic_number -= arabic_number % 100
    if arabic_number % 1000 != 0:
        hundreds = arabic_number % 1000
        roman_hundreds = ARABIC_ROMAN[hundreds]
        arabic_number -= arabic_number % 1000
    if arabic_number % 10000 != 0:
        thousands = arabic_number % 10000
        roman_thousands = ARABIC_ROMAN[thousands]
        
    roman = roman_thousands + roman_hundreds + roman_tens + roman_singles
    return roman

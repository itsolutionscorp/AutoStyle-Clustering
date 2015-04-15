numbers = (1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
symbols = ("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
numerals = zip(numbers, symbols)
MAX = 3000

def numeral(integer):
    result = ''
    while integer:
        integer, num = divmod(integer, MAX)
        romanResult = ""
        for value, rchar in numerals:
            count, num = divmod(num, value)
            romanResult += count * rchar
        if romanResult:
            result += romanResult
    return result

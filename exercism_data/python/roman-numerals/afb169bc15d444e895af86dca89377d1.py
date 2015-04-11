NUMERALS = {1: 'I', 4: 'IV', 5: 'V', 9: 'IX', 10: 'X', 40: 'XL', 50: 'L',
            90: 'XC', 100: 'C', 400: 'CD', 500: 'D', 900: 'CM', 1000: 'M'}


def numeral(arabic):
    roman = []
    for value in sorted(NUMERALS.keys(), reverse=True):
        while arabic >= value:
            roman.append(NUMERALS[value])
            arabic -= value
    result = ''.join(roman)
    if from_roman(result):
        return result


def from_roman(roman):
    result = 0
    index = 0
    for value in sorted(NUMERALS.keys(), reverse=True):
        romnum = NUMERALS[value]
        while roman[index:index + len(romnum)] == romnum:
            result += value
            index += len(romnum)
    return result

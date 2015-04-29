NUMERALS = {"M":1000, "D":500, "C":100, "L":50,"X":10, "V":5, "I":1}

def numeral(num):
    numerals = ''

    if (num / 1000) > 0:
        numerals += "M"*(num / 1000)
        num = num % 1000
    if (num / 500) > 0:
        numerals += "D"*(num / 500)
        num = num % 500
    if (num / 100) > 0:
        numerals += "C"*(num / 100)
        num = num % 100
    if (num / 50) > 0:
        numerals += "L"*(num / 50)
        num = num % 50
    if (num / 10) > 0:
        numerals += "X"*(num / 10)
        num = num % 10
    if (num / 5) > 0:
        numerals += "V"*(num / 5)
        num = num % 5
    if (num / 1) > 0:
        numerals += "I"*(num / 1)
        num = num % 1

    return optimise_roman(numerals)

def optimise_roman(numstr):
    for item in NUMERALS:
        num_count = numstr.count(item)
        if num_count > 3 and item in ['C', 'X', 'I']:
            if item == "C":
                numstr = numstr.replace('CCCC', "CD")
            if item == "X":
                numstr = numstr.replace('XXXX', "XL")
            if item == "I":
                numstr = numstr.replace('IIII', "IV")

        if num_count >= 2 and item in ['D', 'L', 'V']:
            if item == "D":
                new_str = "M"*(num_count/2) + "D"*(num_count%2)
                numstr = numstr.replace(item*num_count, new_str)
            if item == "L":
                new_str = "C"*(num_count/2) + "L"*(num_count%2)
                numstr = numstr.replace(item*num_count, new_str)
            if item == "V":
                new_str = "X"*(num_count/2) + "V"*(num_count%2)
                numstr = numstr.replace(item*num_count, new_str)

        if 'VIV' in numstr:
            numstr = numstr.replace('VIV', 'IX')
        if 'DCD' in numstr:
            numstr = numstr.replace('DCD', 'CM')
        if 'LXL' in numstr:
            numstr = numstr.replace('LXL', 'XC')
    return numstr

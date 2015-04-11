def numeral(number):
    """Returns the roman numeral equivalent of number"""

    mapping = {1000: 'M', 900: 'CM',  500: 'D', 400: 'CD', 100: 'C',  90: 'XC',
               50: 'L',  40: 'XL', 10: 'X',  9: 'IX',   5: 'V',  4: 'IV',   1: 'I'}
    result = []

    # iterate over sorted mapping, including the roman value of any key that divides into number
    for i in sorted(mapping, reverse=True):
        count = int(number // i)
        result.append(count * mapping[i])
        number -= count * i
    return ''.join(result)

""" module roman for exercism.io programming training """


def numeral(arabic):
    table = {1000: 'M', 500: 'D', 100: 'C', 50: 'L', 10: 'X', 5: 'V', 1: 'I'}
    roman_string = ''
    
    def updater(subtract, roman_char):
        nonlocal roman_string, arabic
        roman_string += roman_char
        arabic -= subtract
        
    last_ten, last_five = None, None
    for i in sorted(table.keys(), reverse=True):
        if str(i)[0] == '1':
            if last_ten:
                if arabic >= last_ten - i: updater(last_ten - i, table[i] + table[last_ten])
            if last_five:
                if arabic >= last_five: updater(last_five, table[last_five])
                if arabic >= last_five - i: updater(last_five - i, table[i] + table[last_five])
            while arabic // i > 0: updater(i, table[i])
        if str(i)[0] == '1':
            last_ten = i
        else:
            last_five = i
    return roman_string

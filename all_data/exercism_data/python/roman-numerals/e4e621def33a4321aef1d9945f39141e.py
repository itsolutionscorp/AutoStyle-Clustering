def numeral(n):
    result = ""
    mult = 1
    for digit in reversed(str(n)):
        result= convert(int(digit), magnitude(mult)) + result
        mult*= 10
    return result

def convert(n, one_five_ten):
    result = ""
    if n == 1 or n == 6:
        result+= one_five_ten[0]
    elif n == 2 or n == 7:
        result+= one_five_ten[0] * 2
    elif n == 3 or n == 8:
        result+= one_five_ten[0] * 3
    #two special cases
    elif n == 4:
        return one_five_ten[0] + one_five_ten[1]
    elif n == 9:
        return one_five_ten[0] + one_five_ten[2]
    #check over equal five
    if n > 4:
        result = one_five_ten[1] + result
    return result

def magnitude(n):
    if n // 1000:
        return ("M", "Z", ".")
    if n // 100:
        return ("C", "D", "M")
    if n // 10:
        return ("X", "L", "C")
    else:
        return ("I", "V", "X")
"""
M MM MMM MZ Z ZM ZMM ZMMM Z. Z thousands
C CC CCC CD D DC DCC DCCC CM M hundreds
X XX XXX XL L LX LXX LXXX XC C tens
I II III IV V VI VII VIII IX X ones
    5 0
    1 6 4
    2 7
    3 8 9
"""

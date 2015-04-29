romanmap = ("", "0", "00", "000", "01", "1", "10", "100", "1000", "02")

def numeral(n):
    result = ""
    mult = 1
    for digit in reversed(str(n)):
        result= convert(int(digit), magnitude(mult)) + result
        mult*= 10
    return result

def convert(digit, one_five_ten):
    result = ""
    for i in romanmap[digit]:
        result += one_five_ten[int(i)]
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

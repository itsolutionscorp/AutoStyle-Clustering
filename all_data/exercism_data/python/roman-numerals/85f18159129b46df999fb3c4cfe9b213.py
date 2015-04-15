romanmap = ("", "0", "00", "000", "01", "1", "10", "100", "1000", "02")
magmap = "IVXLCDMZ."

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
    for x in range(3, 0, -1):
        if n // 10**x:
            return magmap[x*2:x*2+3]

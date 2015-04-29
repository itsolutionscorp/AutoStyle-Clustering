def numeral(num):
    char = 'IVXLCDM'
    value = [1,5,10,50,100,500,1000]
    roman = ''

    for i in (6,4,2,0):
        n = num / value[i]
        if n == 9:
            roman += char[i] + char[i+2]
            num -= value[i] * 9
            n = 0
        elif n >= 5:
            roman += char[i+1]
            num -= value[i] * 5
            n -= 5
        if n == 4:
            roman += char[i] + char[i+1]
        elif n:
            roman += char[i] * n
        num %= value[i]

    return roman

print numeral(9)

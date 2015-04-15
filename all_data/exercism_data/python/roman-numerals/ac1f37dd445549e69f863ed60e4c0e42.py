numMap = {}
numMap[1, 1] = 'I'
numMap[1, 5] = 'V'
numMap[10, 1] = 'X'
numMap[10, 5] = 'L'
numMap[100, 1] = 'C'
numMap[100, 5] = 'D'
numMap[1000, 1] = 'M'
numMap[1000, 5] = ''

posMap = {}
posMap[0] = ''
posMap[1] = '1'
posMap[2] = '11'
posMap[3] = '111'
posMap[4] = '15'
posMap[5] = '5'
posMap[6] = '51'
posMap[7] = '511'
posMap[8] = '5111'
posMap[9] = '1*'

def numeral(n):
    pos = 1
    result = ''
    while n > 0:
        num = n % 10
        result = get_roman(pos, num) + result
        pos = pos * 10
        n =  n / 10
    return result

def get_roman(pos, n):
    one = numMap[pos, 1]
    five = numMap[pos, 5]
    roman = posMap[n].replace('1', one)
    if n < 9:
        return roman.replace('5', five)
    else:
        return roman.replace('*', numMap[pos * 10, 1])

if __name__ == '__main__':
    print "You're calling it wrong"



def numeral(arabic):
    thou, hun, ten, one = split(arabic)
    ret_array = []

    if thou:
        ret_array.append(''.join(['M' for _ in range(1, thou+1)]))

    ret_array += numeral_helper(hun, ['C', 'D', 'M'])
    ret_array += numeral_helper(ten, ['X', 'L', 'C'])
    ret_array += numeral_helper(one, ['I', 'V', 'X'])

    return ''.join(ret_array)


def numeral_helper(base, numerals):
    ret_array = []
    if base:
        if base < 4:
            ret_array.append(''.join([numerals[0] for _ in range(1, base+1)]))
        elif base == 4:
            ret_array.append(''.join(numerals[:2]))
        elif base == 5:
            ret_array.append(numerals[1])
        elif base < 9:
            ret_array.append(numerals[1] + ''.join([numerals[0] for _ in range(1, base-4)]))
        else:
            ret_array.append(''.join(numerals[0]+numerals[2]))

    return ret_array


def split(number):
    thou = int(number / 1000)
    hun = int((number -(thou*1000)) /100)
    ten = int((number -(thou*1000) - (hun*100)) / 10)
    one = int((number -(thou*1000) - (hun*100) - (ten*10)))
    return thou, hun, ten, one


# Not needed but can convert a Numeral back to an arabic number.
def arabic(str_num):
    for num, ara in ((numeral(x), x) for x in range(99999)):
        if num == str_num:
            return ara

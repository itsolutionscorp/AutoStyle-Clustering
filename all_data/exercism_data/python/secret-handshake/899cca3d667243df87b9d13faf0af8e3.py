__author__ = 'tracyrohlin'

import collections

def handshake(n):
    secret_code = {1: "wink", 10: "double blink", 100: "close your eyes", 1000: "jump"}
    secret_handshake = []

    if type(n) == int:
        absn = abs(n)
        converted_n = bin(absn)
        bin_list = converted_n.split("b")
        reduced_bin = bin_list[1]
        bin_num = int(reduced_bin)
        if n <= 0:
            return secret_handshake
        check_for_reverse = str(bin_num)
    else:
        bin_num = int(n)
        bin_list = [int(i) for i in str(bin_num)]
        for i in range(2, 10):
            if i in bin_list:
                return secret_handshake
        check_for_reverse = str(bin_num)

    keys = secret_code.keys()
    keys = sorted(keys, reverse=True)

    for key in keys:
        if bin_num >= 10000:
            bin_num -= 10000
        if bin_num - key >= 0:
            secret_handshake.append(secret_code[key])
            bin_num -= key


    if int(check_for_reverse) >= 10000:
        return secret_handshake
    else:
        secret_handshake.reverse()
        return secret_handshake

def code(secret_list):
    number_code = 0
    numbers = collections.OrderedDict([("wink", 1), ("double blink", 10), ("close your eyes", 100), ("jump", 1000)])
    keys = numbers.keys()

    for action in secret_list:
        if action not in keys: #checks to see if item is in dictionary
            return str(0)
        else:
            number_code += numbers[action]
    if all(numbers[f] < numbers[s] for f, s in zip(secret_list, secret_list[1:])) == False: #checks to see if items are out of order
        number_code += 10000

    return str(number_code)

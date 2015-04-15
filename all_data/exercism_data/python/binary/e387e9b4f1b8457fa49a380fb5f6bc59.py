def parse_binary(num):
    total = 0
    for i, bit in enumerate(num[::-1]):
        if (bit != '1') and (bit != '0'):
            raise ValueError
        elif bit == '1':
            total += 2**(i)
    return total

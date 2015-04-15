def hexa(hex):
    def hex_digit_to_num(d):
        if ord(d) in range(ord('0'), ord('9') + 1): #0-9
            return int(d)
        elif ord(d.upper()) in range(ord('A'), ord('F') + 1):
            return ord(d.upper()) - ord('A') + 10
        else:
            raise ValueError
    if len(hex) == 0:
        return 0
    return hex_digit_to_num(hex[-1]) + 16 * hexa(hex[:-1])

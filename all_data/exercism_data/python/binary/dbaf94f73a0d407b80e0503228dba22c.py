def parse_binary(binary):
    decimal_value = 0
    bitvalue = 1

    for char in reversed(binary):
        if char == "0":
            pass 
        elif char == "1":
            decimal_value += bitvalue
        else:
            raise ValueError("Illegal character '%s' in binary number" % char)
        bitvalue <<= 1

    return decimal_value

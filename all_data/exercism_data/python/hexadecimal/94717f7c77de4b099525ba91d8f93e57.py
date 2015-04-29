def hexa(hex_str):
    hex_list = list(hex_str.lower())
    dec = 0
    cur = 0
    while hex_list:
        dig = hex_list.pop()
        ord_dig = ord(dig)
        if ord_dig >= ord('a') and ord_dig <= ord('f'):
            dec += (ord_dig - ord('a') + 10) * (16**cur)
        elif ord_dig >= ord('0') and ord_dig <= ord('9'):
            dec += (ord_dig - ord('0')) * (16**cur)
        else:
            raise ValueError("Input must be a valid hexadecimal number.")
        cur += 1
    return dec

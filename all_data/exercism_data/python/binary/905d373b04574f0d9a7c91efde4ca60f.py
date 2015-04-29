def parse_binary(bin):
    bin = list(bin)
    dec = 0
    cur = 0
    while bin:
        dig = bin.pop()
        if dig == '1':
            dec += 2**cur
        elif dig != '0':
            raise ValueError('Input string must contain only 0s and 1s.')
        cur += 1
    return dec

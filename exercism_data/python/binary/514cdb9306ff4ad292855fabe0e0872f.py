def parse_binary(s, num=0):
    if len(s) == 0: return num >> 1
    if s[0] == '1': return parse_binary(s[1:], (num+1)<<1)
    if s[0] == '0': return parse_binary(s[1:], num<<1)
    raise ValueError

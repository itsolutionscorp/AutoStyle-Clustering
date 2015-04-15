def parse_binary(s):
    result = 0
    for i in s:
        if not (i == '0' or i == '1'):
            raise ValueError
        result = (result << 1) + int(i)
    return result

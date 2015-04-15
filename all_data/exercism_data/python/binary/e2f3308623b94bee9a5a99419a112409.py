def parse_binary(binary):
    if not isinstance(binary, str) or set(binary) - set(['0', '1']):
        raise ValueError('Invalid binary input.')

    return sum(int(i) * 2 ** (len(binary) - n)
                for n, i in enumerate(binary, 1))

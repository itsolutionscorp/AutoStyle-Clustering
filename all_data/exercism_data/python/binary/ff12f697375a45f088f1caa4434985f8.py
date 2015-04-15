def parse_binary(b):
    acceptable = {'0', '1'}
    if set(b).union(acceptable) != acceptable:
        raise ValueError("Input not a valid binary representation")
    return sum(2 ** i * int(c) for i, c in enumerate(b[::-1]))

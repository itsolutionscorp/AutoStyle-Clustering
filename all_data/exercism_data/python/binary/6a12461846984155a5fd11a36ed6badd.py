def parse_binary(code):
    if len(code) == 0 or any([x not in ['0', '1'] for x in code]):
        raise ValueError
    return sum([2 ** i if x == '1' else 0 for i, x in zip(range(len(code)), reversed(code))])

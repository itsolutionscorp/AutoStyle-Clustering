def hexa(h):
    digits = {v: i for i, v in enumerate('0123456789ABCDEF')}
    h = h.upper()
    try:
        nums = [digits[v] << (i * 4) for i, v in enumerate(h[::-1])]
    except KeyError:
        raise ValueError("Input not a valid hexadecimal representation.")
    return sum(nums)

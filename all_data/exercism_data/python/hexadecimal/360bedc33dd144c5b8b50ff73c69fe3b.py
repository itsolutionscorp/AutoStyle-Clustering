def hexa(s):
    result = 0
    for i in s:
        result = result * 16 + hexto10digit(i)
    return result


def hexto10digit(s):
    if 0 <= ord(s) - ord('0') <= 9:
        return ord(s) - ord('0')
    if 0 <= ord(s.lower()) - ord('a') <= 5:
        return 10 + ord(s.lower())-ord('a')
    raise ValueError

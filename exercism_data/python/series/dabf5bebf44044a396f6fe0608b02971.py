def slices(seq, n):
    result = []
    num = []
    if n > len(seq) or n == 0:
        raise ValueError
    for index in range(0, len(seq) - n + 1):
        build_series(seq, index, num, n, result)
    return result

def build_series(seq, index, num, n, result):

    number = list(num)
    if n == 0:
        result.append(number)
        return
    number.append(int(seq[index]))
    build_series(seq, index + 1, number, n - 1, result)

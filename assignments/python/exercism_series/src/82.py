def slices(num, n):
    if n == 0 or n > len(num):
        raise ValueError('Improper length for series.')
    num_list = map(lambda x: int(x), list(num))
    if len(num_list) == n:
        return [num_list]
    return [num_list[0:n]] + slices(num_list[1:], n)

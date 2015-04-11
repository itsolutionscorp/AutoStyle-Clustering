def largest_product(series, n):
    if n > len(series):
        raise ValueError('ERROR: series exceeds length of seed')

    num_list = slices(series, n)
    current_product = 1
    largest = 1

    for l in num_list:

        for num in l:
            current_product *= num

        if current_product > largest:
            largest = current_product

        current_product = 1

    return largest


def slices(series, n):
    if n > len(series):
        raise ValueError('ERROR: series exceeds length of seed')

    num_list = []

    for l in range(len(series) - (n - 1)):
        count = l
        cur_list = []
        for c in range(n):
            cur_list.append(int(series[count]))
            count += 1
        num_list.append(cur_list)

    return num_list

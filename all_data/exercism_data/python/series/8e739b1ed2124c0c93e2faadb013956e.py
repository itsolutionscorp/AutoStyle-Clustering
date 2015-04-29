def slices(numbers, length):
    if length < 1 or length > len(numbers):
        raise ValueError('Length should not exceed number of digits')

    combos = []
    number_list = [int(i) for i in numbers]

    while len(number_list) >= length:
        combos.append(number_list[:length])
        number_list.pop(0)

    return combos


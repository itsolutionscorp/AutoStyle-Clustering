def slices(numbers, series_length):
    if len(numbers) < series_length or series_length < 1:
        raise ValueError

    slices = []
    num_slices = len(numbers) - series_length + 1

    for i in range(num_slices):
        slices.append([int(char) for char in numbers[i:i + series_length]])

    return slices

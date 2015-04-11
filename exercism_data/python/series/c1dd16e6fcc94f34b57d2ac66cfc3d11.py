def slices(numbers, series_length):
    sequence_length = len(numbers)
    if sequence_length < series_length or series_length < 1:
        raise ValueError('The series length specified ({}) is not valid!'.format(series_length))

    slices = []
    num_slices = sequence_length - series_length + 1

    for i in range(num_slices):
        slices.append([int(char) for char in numbers[i:i + series_length]])

    return slices

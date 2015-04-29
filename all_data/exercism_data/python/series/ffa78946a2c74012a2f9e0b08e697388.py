

def slices(number_string, series_length):

    number_length = len(number_string)

    if not number_string.isnumeric():
        raise ValueError
    if series_length > number_length or series_length < 1:
        raise ValueError

    return [[int(number_string[i + j])
             for j in range(series_length)]
             for i in range(number_length - series_length + 1)]



def slices(number_string, series_length):

    number_length = len(number_string)

    if not number_string.isnumeric():
        raise ValueError
    if series_length > number_length or series_length < 1:
        raise ValueError

    number_list = [int(number_string[i]) for i in range(number_length)]
    
    return [number_list[i : i + series_length]
            for i in range(number_length - series_length + 1)]

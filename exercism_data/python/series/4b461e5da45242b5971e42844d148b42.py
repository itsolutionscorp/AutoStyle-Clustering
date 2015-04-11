def slices(digits, length_of_series):
    
    is_series_longer_than_digits = length_of_series > len(digits)
    is_series_less_than_1 = length_of_series < 1

    if is_series_longer_than_digits: raise ValueError
    if is_series_less_than_1: raise ValueError
    
    def remove_first_element(L):
        L.pop(0)
    
    series = []
    
    number_of_series = (len(digits) - length_of_series) + 1

    digits = list(map(int, digits))

    for _ in range(number_of_series):
        series.append(digits[0:length_of_series])
        remove_first_element(digits)

    return series

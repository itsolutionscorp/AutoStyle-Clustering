def slices(string, series_size):
    series_list = []
    current_series = []
 
    if series_size == 0:
        raise ValueError('Cannot create slices of size 0')
    elif series_size > len(string):
        raise ValueError('Cannot create slices bigger than length of the string')

    for i in range(len(string)):
        if len(string) - i >= series_size:
            for c in string[i:series_size+i]:
                current_series.append(int(c))
            series_list.append(current_series)
            current_series = []

    return series_list

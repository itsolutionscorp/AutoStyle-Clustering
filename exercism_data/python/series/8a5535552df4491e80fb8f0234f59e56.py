def slices(series, slice):
    slice_too_large = slice > len(series)
    slice_too_small = slice < 1

    if slice_too_small or slice_too_large:
        raise ValueError
    else:
        slices = []
        series_list = [letter for letter in series]
        
        #for s in range(len(series) - slice + 1):
           # slices.append([int(series[letter+s]) for letter in range(slice)])
            
        while len(series_list) >= slice:
            slices.append([int(series_list[letter]) for letter in range(slice)])
            series_list = series_list[1:]
    return slices
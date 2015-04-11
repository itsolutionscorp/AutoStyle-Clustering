
def slices(series,slice_length):
    series_list = []
    if slice_length>len(series) or slice_length==0:
        raise ValueError
    else:
        for slice_starter in range(len(series)-slice_length+1):
            slice_end=slice_starter + slice_length
            slice_builder = []
            for i in range(slice_length):
                digit = int(series[slice_starter:slice_end][i])
                slice_builder.append(digit)
            series_list.append(slice_builder)
    return series_list
    

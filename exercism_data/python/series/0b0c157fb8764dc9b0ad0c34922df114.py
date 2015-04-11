def slices(input, window_size):
    if window_size not in range(1, len(input)+1):
        raise ValueError('no')

    return [map(lambda x: int(x), input[i:window_size+i]) for i in range(0, (len(input) - window_size)+1)]

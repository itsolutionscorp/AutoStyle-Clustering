def slices(source, size):
    source_size = len(source)
    if 1 <= size <= source_size:
        last_slice_index = source_size - size 
        return [[int(j) for j in source[i:i+size]] for i in range(last_slice_index + 1)]
    else:
        raise ValueError('1 <= size : %d <= length of "%s" (%d)' % (size, source, source_size))

import string
def slices(chunk, slice_size):
    if 0 >= slice_size or slice_size > len(chunk):
        raise ValueError("You done gone derp the size.")
    herp = []
    chunk_size = len(chunk)
    for i in range(0, chunk_size):
        herp.append([int(num) for num in chunk[i:i+slice_size]])
        if i + 1 + slice_size > chunk_size:
            break
    return herp

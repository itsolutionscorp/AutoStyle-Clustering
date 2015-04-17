__author__ = 'emiller42'
def slices(sequence, length):
    if length == 0:
        raise ValueError("Length argument must be > 0")
    if len(sequence) < length:
        raise ValueError("Length argument cannot be larger than the length of the sequence")
    return [map(int, sequence[i:i+length]) for i in range(len(sequence) - length + 1)]

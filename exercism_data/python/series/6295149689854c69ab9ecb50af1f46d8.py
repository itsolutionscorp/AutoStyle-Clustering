def slices(sample, length):
    if len(sample) < length or length == 0:
        raise ValueError, "Series length is larger than string of digits.\nRepeat with larger string."
    return [[int(series) for series in sample[i:i+length]]
            for i in range(len(sample)) if len(sample[i:i+length]) == length]

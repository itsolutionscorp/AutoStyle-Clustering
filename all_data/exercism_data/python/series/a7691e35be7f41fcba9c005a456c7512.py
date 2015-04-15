def slices(series, length):
    if length > 0 and length <= len(series):
        return [map(int, list(series[i:i + length])) for i
                in range(0, len(series) - length + 1)]
    raise ValueError('invalid length: %d' % (length))

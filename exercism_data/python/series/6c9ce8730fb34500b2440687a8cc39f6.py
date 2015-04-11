def slices(series, slice_size):

    if slice_size > 0 and slice_size <= len(series):
        shifts = range(len(series) - slice_size + 1)
        slices = [series[shift:shift + slice_size] for shift in shifts]
        return [map(int, list(slice)) for slice in slices]

    elif slice_size > len(series):
        error_message = 'slice_size %s can not be greater than '\
                        'series length %s.'
        raise ValueError(error_message % (slice_size, len(series)) )

    else:
        raise ValueError('invalid slice_size %s' % slice_size )

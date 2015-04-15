def slices(series, size):

    if size > len(series) or size < 1:
        raise ValueError(
            'Size of the slices cannot be bigger than ' + \
            'the length of the numeric series')

    return [_char_list_to_int_list(slice)
                for slice in _get_char_lists(series, size)]


def _get_char_lists(series, size):

    return _get_char_lists_from_substrings(
        _get_substrings_size_n(series, size))


def _get_substrings_size_n(series, size):

    return [series[i:i+size] for i in xrange(len(series) + 1 - size)]


def _get_char_lists_from_substrings(substrings):

    return [list(substring) for substring in substrings]


def _char_list_to_int_list(chars):

    return [int(ch) for ch in chars]

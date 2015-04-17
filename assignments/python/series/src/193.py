from toolz import sliding_window
def slices(input_string, n):
    if n > len(input_string) or n == 0:
        raise ValueError
    input_ = [int(c) for c in input_string]
    return [list(window) for window in sliding_window(n, input_)]

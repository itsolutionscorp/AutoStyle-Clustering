import string


def slices(series: string, slice_size: int) -> list:
    if 0 >= slice_size or slice_size > len(series):
        raise ValueError("You done gone derp the size.")

    return [
        series[int(i): int(i) + slice_size]
        for i in range(len(series) - slice_size + 1)
    ]

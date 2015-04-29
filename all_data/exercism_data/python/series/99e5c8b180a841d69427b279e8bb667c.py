__author__ = 'agupt15'


def slices(series, num):
    series = series.strip()
    if len(series) < num or num == 0:
        raise ValueError

    result = []
    max_index = len(series)

    for idx in range(max_index):
        if idx + num <= max_index:
            result.append([int(n) for n in series[idx:num+idx]])
    return result

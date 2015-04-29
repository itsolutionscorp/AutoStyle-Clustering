def slices(series, times):
    if times < 1 or times > len(series):
        raise ValueError
    out = []
    subset = -times + 1 if times != 1 else len(series)
    for i, char in enumerate(series[:subset]):
        sub_series = []
        for j in range(i, i+times):
            sub_series.append(int(series[j]))
        out.append(list(sub_series))
    return out

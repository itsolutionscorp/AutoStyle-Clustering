__author__ = 'tracyrohlin'
def slices(series, n):
    series = list(series)
    series = [int(x) for x in series]
    series_list = []
    i = 0
    if len(series) >= n:
        if n != 0:
            for x in range(len(series)):
                if x+n-1 < len(series):
                    series_list.append(series[x:x+n])
            return series_list
        else:
            raise ValueError()
    else:
        raise ValueError()

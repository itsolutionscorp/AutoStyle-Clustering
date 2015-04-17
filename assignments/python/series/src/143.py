'''
Created on Sep 26, 2014
@author: adsmith
'''
def slices(series, slice_length):
    if slice_length > len(series) or slice_length < 1:
        raise ValueError("a meaningful error message")
    results = []
    for start_idx in range(0, len(series) - slice_length + 1):
        end_idx = start_idx + slice_length
        results.append( list(series[start_idx:end_idx]) )
    return [list(map(int, result)) for result in results]

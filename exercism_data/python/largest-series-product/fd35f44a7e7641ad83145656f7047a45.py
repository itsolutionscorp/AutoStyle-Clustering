def slices(series,length):

    if length < 1 or length > len(series):
        raise ValueError("You gettin' the AIDS now.")
    results = []
    for i in range(len(series)-length+1):
        results.append([int(x) for x in series[i:i+length]])
    return results

def largest_product(series, length):
    if len(series) == length == 0: return 1
    test_slices = slices(series,length)
    largest = 0
    for s in test_slices:
        prod = reduce(lambda x,y: x*y, s)  
        largest = prod > largest and prod or largest
    return largest

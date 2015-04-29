def slices(series,length):

    if length < 1 or length > len(series):
        raise ValueError("You gettin' the AIDS now.")
    results = []
    for i in range(len(series)-length+1):
        results.append([int(x) for x in series[i:i+length]])
    return results

def largest_product(series, length):
        
    return max([reduce(lambda x,y: x*y, s) for s in slices(series,length)]) if length >0 else 1  
        
    

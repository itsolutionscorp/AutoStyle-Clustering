def slices(series,length):
    result = list()
    if length < 1:
        raise(ValueError("slice length too short: "+length.__str__()))
    if length > series.__len__():
        raise(ValueError("slice length too long: "+length.__str__() +
                         ', series length is: ' + series.__len__().__str__()))
    for i in range(series.__len__() - length +1): 
        slice = list()
        for slicechar in series[i:i+length]: #build the slice as a list
            slice.append(int(slicechar)) #convert character to integer
        result.append(slice)
    return result

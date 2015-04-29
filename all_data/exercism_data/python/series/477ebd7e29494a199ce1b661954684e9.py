def slices(inputString, sliceLength):
    def sliceHelper(startPos):
        if startPos + sliceLength <= len(inputString):
            series = []
            slice = inputString[startPos:(startPos + sliceLength)]
            for letter in slice:
                series.append(int(letter))
                
            nonlocal result
            result.append(series)
            sliceHelper(startPos+1)
            
    if sliceLength < 1 or sliceLength > len(inputString):
        raise ValueError('Invalid slice length')
    result = []
    sliceHelper(0)
    return result

def slices(inStr, outLen):
    if outLen==0 or outLen>len(inStr):
        raise ValueError
    return [list(map(int, inStr[i:i+outLen])) for i in range(len(inStr)+1-outLen)]

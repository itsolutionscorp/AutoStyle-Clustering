def slices(sequence, slicesize):

    allslices = []

    sequence = map(int, sequence)
    
    if len(sequence) < slicesize:
        raise ValueError("Slice length larger than the input sequence length.")
    if slicesize == 0:
        raise ValueError("Slice must have a non-zero length.")

    for i in range(len(sequence)):
        if len(sequence[i:i+slicesize]) == slicesize:
            allslices.append(sequence[i:i+slicesize])
    
    return allslices

def largest_product(sequence, slicesize):
        
    if len(sequence) == 0: #don't get the purpuse of test_identity()
        return 1

    allslices = slices(sequence, slicesize)
    products = []    

    for i in allslices:
        product = 1
        for x in i:
            product *= x
        products.append(product)
    
    return sorted(products)[-1]

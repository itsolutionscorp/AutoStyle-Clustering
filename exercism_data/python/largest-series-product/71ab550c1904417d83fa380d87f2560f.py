def slices(string, size):
    if size>len(string):
        raise ValueError

    arrays = []
    for start in range(len(string)-size+1):
        array = []
        for num in string[start:start+size]:
            array.append(int(num))
        arrays.append(array)
    return arrays

def largest_product(string, size):
    product_slice = slices(string, size)
    
    product_max = 0
    for slice in product_slice:
        product = 1
        for number in slice:
            product *= number
        
        if product > product_max:
            product_max = product
        
    return product_max

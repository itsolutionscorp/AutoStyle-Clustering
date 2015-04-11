from numpy import prod

def largest_product(digits, slice_length):
    
    slice_list = slices(digits, slice_length)  
    products = []
    
    for i in range(0, len(slice_list)):
        products.append(prod(slice_list[i]))
        
    return max(products)
    
def slices(digits, slice_length):
    "Return all slices of given length from digit string"
    
    if slice_length > len(digits):
         raise ValueError('Slice length exceeds length of digit string.')
    
    n = 0
    slices = []
    while len(digits) - n >= slice_length:
        
        slices.append(map(int, digits[n:n+slice_length]))
        n += 1
    
    return slices

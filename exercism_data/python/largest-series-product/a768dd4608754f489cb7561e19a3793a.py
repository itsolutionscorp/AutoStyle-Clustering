# Largest Series Product
from functools import reduce

def slices(number_string, length): 
    if length > len(number_string):
        raise ValueError("Make sure the length fits the series!")
    
    else:    
        result = []
        
        for i in range(0, len(number_string) - length + 1):
            result.append(list(map(int, number_string[i:i + length])))
            
        return result

def largest_product(digits_string, length):     
    if len(digits_string) == 0:
        return 1
        
    else:    
        products_list = []
        
        slices_list = slices(digits_string, length)
        
        for digits_slice in slices_list:
            products_list.append(reduce(lambda x, y: int(x) * int(y), digits_slice))
               
        return max(products_list)

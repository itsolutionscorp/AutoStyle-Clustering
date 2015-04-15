def slices(series,length):
    if len(series) < length:
        raise ValueError
    if length < 1:
        return [[1]]
    
    main_list= []   
    while len(series) >= length:
        main_list.append([int(series[index]) for index in range(length)])
        series = series[1:]
    return main_list
    

def prod(num_list):
    def product(val_a,val_b): return val_a * val_b
    
    return reduce(product,num_list)
    
def largest_product(series,length):
	largest_prod = 0
	
	for serie_list in slices(series,length):
	    temp_result = prod(serie_list)
	    if temp_result > largest_prod:
	        largest_prod = temp_result
	
	return largest_prod

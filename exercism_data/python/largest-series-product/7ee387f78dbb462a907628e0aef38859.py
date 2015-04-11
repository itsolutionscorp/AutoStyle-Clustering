def slices(num_string, slice_length):
    
    # Raise an error if the slice length is too long.
    if slice_length > len(num_string):
        raise ValueError('Slice length greater than length of string to slice.')

	# Get the available slices.
    slices_list = []
    for slice in range(len(num_string)-slice_length+1):
        current_slice = num_string[slice:slice_length+slice]
        # The current_slice is a list of strings. Use a list comprehension to
        # convert each element in the list to an int before appending the
        # current_slice to the slices_list.
        slices_list.append([int(number) for number in current_slice])
    return slices_list

def largest_product(num_string, slice_length):
	# A set to contain our products.
	product_set = set()

	# Loop over the series of slices.
	for current_series in slices(num_string, slice_length):
		product = 1
		
		# Loop over the numbers in the series, multiplying to get the product.
		for number in current_series:
			product *= number
		
		# Add the product to the set.
		product_set.add(product)
		
	# Return the maximum product from the set.
	return max(product_set)

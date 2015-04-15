from functools import reduce
import operator

def slices(num_string, slice_length):
    
    # Raise an error if the slice length is too long.
    if slice_length > len(num_string):
        raise ValueError('Slice length greater than length of string to slice.')

	# Get the available slices.
    slices = []
    for slice_number in range(len(num_string)-slice_length+1):
        current_slice = num_string[slice_number:slice_length+slice_number]

        # The current_slice is a list of strings. Use the map() function to apply
        # the int() function to each string in the list. Wrap this in the list()
        # function to get the results as a list, then append it to the slices list.
        slices.append(list(map(int, current_slice)))
    return slices

def largest_product(num_string, slice_length):
	# A set to contain our products.
	products = set()

	# Loop over the series of slices.
	for current_series in slices(num_string, slice_length):
		# reduce() and operator.mul() to the rescue!
		# Notes for future me:
		#  - operator.mul(a,b) returns the result of multiplying a and b.
		#  - reduce() applies operator.mul() to successive items in the current_series.
		#  - reduce() is given an initializer of 1, so it begins by multiplying 1 by the
		#    first item in the current_series, and multiplies that product by the next
		#    value in the current_series, until it runs out of numbers.
		current_product = reduce(operator.mul, current_series, 1)
		
		# Add the product to the set.
		products.add(current_product)
		
	# Return the maximum product from the set.
	return max(products)

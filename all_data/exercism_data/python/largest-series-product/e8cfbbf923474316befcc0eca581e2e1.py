# Get each substring, convert each to list of chars, 
# then convert to list of integers.
# Return list of all of them.
def slices(digits, series_length):
    if len(digits) < series_length:
        raise ValueError("series length is longer than number of digits")
    return [ [int(x) for x in list(digits[series:series+series_length])] 
             for series in range(0, len(digits)-series_length+1) ]


# Put every product of non-empty slices in a list
# Return largest value. Or 1 if digits is empty(?).
def largest_product(digits, series_length):
    prod_list = [ reduce(lambda x,y: x*y, s) for s in slices(digits, series_length) if s ]
    prod_list.append(1) # for the weird identity case
    return max(prod_list)

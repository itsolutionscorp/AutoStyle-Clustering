def largest_product(number, series):
	sliced_number = sorted(slices(number,series))
	product = 1
	product_list = []

	if len(sliced_number) > 0:
		for grouping in sliced_number:
			for x in grouping:
				product *= x
			product_list.append(product)
			product = 1	
		return max(product_list)
	else:
		return(1)

def slices(number, series):
	sequences_found = []
	if len(number) >= series:
		sequences_found.extend([(number[i:i+series]) for i in range(0, len(number), series) if len(number[i:i+series]) >= series])
		if(series > 1):
			sequences_found.extend([(number[i:i+series]) for i in range(1, len(number), series) if len(number[i:i+series]) >= series])
		if(series > 2):
			sequences_found.extend([(number[i:i+series]) for i in range(2, len(number), series) if len(number[i:i+series]) >= series])
	else:
		raise ValueError("Check your values")
	new_list = []
	transfer_list = []
	for x in sequences_found:
		for digit in x:
			transfer_list.append(int(digit))
		new_list.append(transfer_list)
		transfer_list = []
	return(new_list)

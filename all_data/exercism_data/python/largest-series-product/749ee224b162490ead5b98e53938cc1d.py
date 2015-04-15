import Queue

def slices(seq, slice_len):
	if slice_len > len(seq):
		raise ValueError('slice length larger than sequence length')
	else:
		list_of_seqs = []

		for n in range(len(seq)):
			temp_n = n
			current_seq = Queue.Queue()
			#Build sequences only if there are consecutive entries in numbers array
			while temp_n != len(seq) and (current_seq.qsize() < slice_len):
				#print temp_n
				current_seq.put(int(seq[temp_n]))
				temp_n += 1

			if not current_seq.empty() and current_seq.qsize() == slice_len:
				temp_list = []
				while not current_seq.empty():
					temp_list.append(current_seq.get())
				
				list_of_seqs.append(temp_list)

		return list_of_seqs

def largest_product(seq, slice_len):
	
	list_of_seqs = slices(seq, slice_len)
	products = []
	if not list_of_seqs:
		return 1
	else:
		for list_seq in list_of_seqs:
			product = 1
			for i in range(slice_len):
				product *= list_seq[i]

			products.append(product)

		return max(products)

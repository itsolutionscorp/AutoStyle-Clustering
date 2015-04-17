import Queue
def slices(seq, slice_len):
	if slice_len > len(seq) or slice_len == 0:
		raise ValueError('slice length larger than sequence length')
	else:
		list_of_seqs = []
		for n in range(len(seq)):
			temp_n = n
			current_seq = Queue.Queue()
			while temp_n != len(seq) and (current_seq.qsize() < slice_len):
				current_seq.put(seq[temp_n])
				temp_n += 1
			if not current_seq.empty() and current_seq.qsize() == slice_len:
				temp_list = []
				while not current_seq.empty():
					temp_list.append(current_seq.get())
				list_of_seqs.append(temp_list)
		return list_of_seqs

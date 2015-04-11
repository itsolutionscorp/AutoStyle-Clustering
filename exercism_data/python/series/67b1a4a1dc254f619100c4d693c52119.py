

def slices(seq, choose):
	if choose > len(seq) or choose == 0:
		raise ValueError("Sequence not long enough")
	answers = []
	buffer = []
	for i in range(len(seq) - choose + 1):
		for j in range(i,i+choose):
			buffer.append(int(seq[j]))
		answers.append(buffer)
		buffer = []
	return answers

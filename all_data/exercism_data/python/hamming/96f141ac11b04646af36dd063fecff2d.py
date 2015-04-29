dna_nuecleotides = ['A', 'G', 'C', 'T']

def distance(sequence1, sequence2):
	#same distance	
	if len(sequence1) == len(sequence2):
		return get_distance(sequence1, sequence2)
	#if distances are different take the shortest
	elif len(sequence1) != len(sequence2):
		if len(sequence1) < len(sequence2):
			return get_distance(sequence1, sequence2[:len(sequence1)])
		else:
			return get_distance(sequence1[:len(sequence2)], sequence2)

def is_valid_nucleotid(nucleotide):
	if nucleotide in dna_nuecleotides:
		return True
	else:
		raise Exception("Unknown nucleotide")

def get_distance(sequence1, sequence2):
	distance = 0
	for nucleotide in range(len(sequence1)):
			nucleotide1 = sequence1[nucleotide]
			nucleotide2 = sequence2[nucleotide]

			if is_valid_nucleotid(nucleotide1) and is_valid_nucleotid(nucleotide2):
				if nucleotide1 != nucleotide2:
					distance +=1
	return distance

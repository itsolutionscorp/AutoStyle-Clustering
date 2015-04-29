# Exercism Python exercise: Hamming
def distance(strand_one, strand_two):
	if len(strand_one) == len(strand_two):
		count = 0
		for i in range(len(strand_one)):
			if strand_one[i] != strand_two[i]:
				count += 1
			else:
				count = count
		return count
	else:
		print "The strands are not the same length."

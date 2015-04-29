def hamming(str1, str2):
	lengths = [len(str1), len(str2)]
	ham_dist = max(lengths) - min(lengths)
	for i in range(min(lengths)):
		if str1[i]!=str2[i]:
			ham_dist+=1
	return ham_dist

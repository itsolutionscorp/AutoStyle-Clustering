def hamming(a,b):
	diff = abs(len(a)-len(b))
	ham = diff
	length = (max(len(a),len(b))) - diff
	i = 0
	while True:
		if i + 1 > length:
			break
		if a[i] != b[i]:
			ham += 1
		i+=1
	return ham

print (hamming('AAGCTAC','ACGTT'))

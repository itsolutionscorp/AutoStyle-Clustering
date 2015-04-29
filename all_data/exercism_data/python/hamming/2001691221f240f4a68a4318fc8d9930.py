def distance(alpha, beta):
	i = 0
	ham = 0 
	while i < len(alpha):
		if alpha[i] != beta[i]:
			ham = ham + 1
			i = i + 1
		else:
			i = i + 1
	return ham

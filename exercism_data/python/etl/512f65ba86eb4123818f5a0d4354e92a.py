
def transform(old):
	answer = {}
	for i in old:
		for p in old[i]:
			answer[(p.lower())] = i
	return answer

#old = {1: ['WORLD', 'BALL'], 2: ['Basket']}
#transform(old)

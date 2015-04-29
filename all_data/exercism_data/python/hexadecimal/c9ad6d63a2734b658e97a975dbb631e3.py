from string import digits
table = {'A': 10, 'B': 11, 'C': 12, 'D': 13, 'E': 14, 'F': 15}
for d in digits:
	table[d] = int(d)

def powers_of_sixteen():
	n = 1
	while True:
		yield n
		n *= 16

def hexa(num):
	num = num.upper()
	result = 0
	for v, p in zip(reversed(num), powers_of_sixteen()):
		if v not in table:
			raise ValueError
		result += table[v] * p
	return result

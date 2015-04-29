def slices(s, n):
	if len(s) < n or n < 1:
		raise ValueError("n is bigger then the number") 
	total = []
	for x in range(len(s)-n+1):
		total.append(chop(s[x:x+n:1]))
	return total

def chop(s):
	total = []
	for digit in s:
		total.append(int(digit))
	return total

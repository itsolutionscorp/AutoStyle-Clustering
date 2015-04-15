import re

sc = {}
t = '''A, E, I, O, U, L, N, R, S, T       1
D, G                               2
B, C, M, P                         3
F, H, V, W, Y                      4
K                                  5
J, X                               8
Q, Z                               10'''


for line in t.splitlines():
	line = re.sub(r'[\s\t]+', '', line)
	match = re.search(r'([^\d]+)(\d+)', line)
	letters = match.group(1)
	score = match.group(2)
	sc = dict( (l, score) for l in letters.split(','), **sc)

def score(text):
	result = 0
	text = re.sub(r'[^a-zA-Z]+', '', text)
	for t in text.upper():
		result += int(sc[t])
	return result

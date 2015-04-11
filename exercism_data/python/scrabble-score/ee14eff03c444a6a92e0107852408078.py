scores = {
	1:  ["A", "E", "I", "O", "U", "L", "N", "R", "S", "T"],
	2:  ["D", "G"],
	3:  ["B", "C", "M", "P"],
	4:  ["F", "H", "V", "W", "Y"],
	5:  ["K"],
	8:  ["J", "X"],
	10: ["Q", "Z"]
}

inv_scores = {}
for val, chars in scores.items():
	for c in chars:
		inv_scores[c] = val

def score(word):
	score = 0
	for c in word:
		score += inv_scores.get(c.upper(), 0)
	return score

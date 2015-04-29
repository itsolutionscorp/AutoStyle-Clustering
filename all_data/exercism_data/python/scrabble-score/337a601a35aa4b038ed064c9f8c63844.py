def score(word):
  scoreTable = [['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
		['D', 'G'],
		['B', 'C','M', 'P'],
		['F', 'H', 'V', 'W', 'Y'],
		['K'],
		['J', 'X'],
		['Q', 'Z']]
  posScore = {0:1, 1:2, 2:3, 3:4, 4:5, 5:8, 6:10}
  score = 0
  word = word.upper()
  for l in word:
    for p in scoreTable:
      if l in p:
	score += posScore[scoreTable.index(p)]
  return score

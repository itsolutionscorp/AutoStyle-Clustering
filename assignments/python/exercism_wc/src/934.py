import collections
from collections import Counter
def word_count(word):
	cnt = Counter()
	temp = []
	tempStr = ""
	tempLen = len(word)
	tempCount = 0
	print tempLen
	for l in word:
		if l != " " and l != "\n":
			tempStr += l
			print tempStr
		else:
			if tempStr != "":
				temp.append(tempStr)
				tempStr = ""
		if tempCount == tempLen-1:
			temp.append(tempStr)
		print tempCount
		tempCount += 1
	for l in temp:
		cnt[l] += 1
	return cnt
print(word_count("yo"))

import string

def detect_anagrams(anagram, words):
	results = []
	for i in words:
		if len(anagram) == len(i):
			if anagram.lower() != i.lower():
				an = list(anagram.lower())
				wo = list(i.lower())

				isAnagram = True

				for x in an:
					if an.count(x) == wo.count(x):
						pass
					else:
						isAnagram = False
						break

				if isAnagram == True:
					results.append(i)

				# if i.lower().translate(string.maketrans(anagram.lower(), ' '*len(i))).strip() == '':
				# 	results.append(i)
	return results

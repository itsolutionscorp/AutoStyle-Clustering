def detect_anagrams(word, phrase):
	# for i in phrase:
	# 	if len(word) == len(i):
	# 		if set(word) == set(i):
	# 			return [i]
	return [x for x in phrase
			if len(word) == len(x) and sorted(list(word.lower())) == sorted(list(x.lower()))
			and word.lower() != x.lower()]
	
# # print(detect_anagrams('ant', 'tan stand at'.split()))
# print(detect_anagrams('Orchestra',
#                             'cashregister Carthorse radishes'.split()))
print(detect_anagrams('galea', ['eagle']))
print(detect_anagrams('banana', ['banana']))

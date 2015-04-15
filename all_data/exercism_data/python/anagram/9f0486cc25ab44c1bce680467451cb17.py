import itertools
def  detect_anagrams(word,list2):
	""" return list of words that match word anagram"""
	list1=[]
	for item in list2:
		list1.append(item)
	combos=list(map("".join, itertools.permutations(word.lower())))
	ul_list=[]
	for word1 in combos:
		ul_list.append(word1)
		ul_list.append(word1.title())
	combos1=set(ul_list)
	list_words=[]
	for i in range(len(list1)):
		if list1[i] in combos1:
			if list1[i]!=word and list1[i]!=word.title():
				list_words.append(list1[i])

	return list_words

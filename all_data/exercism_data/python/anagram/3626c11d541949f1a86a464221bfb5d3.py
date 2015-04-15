#recieve a string, and an array of strings. 
#returns anagrams of the original string from the array

def detect_anagrams(word, array): 
	anagrams = []
	for x in array: 
		if is_anagram(word, x): 
			anagrams.append(x)
	
	return anagrams

def is_anagram(a, b): 
#returns true if b is an anagram of a
	a = a.lower()
	b = b.lower()
	
	if a == b: 
		return False
	
	if counted_dict(a) == counted_dict(b): 
		return True
	else: 
		return False
	
	
def counted_dict(word): 
	dict = {}
	for letter in word: 
		try: 
			dict[letter] += 1
		except: 
			dict[letter] = 1
	return dict
	

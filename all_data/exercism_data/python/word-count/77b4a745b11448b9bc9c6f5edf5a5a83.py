def word_count(phrase):
	dic = {}
	wordlist = [y.strip(' ') for z in [x.split(' ') 
	                         for x in phrase.splitlines()] 
	                         for y in z]
	for word in wordlist:
                if word == "":
                        pass
		elif word in dic:
			dic[word] += 1
		else:
			dic[word] = 1
	return dic

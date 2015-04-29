def detect_anagrams(word, trys):
	anagrams=[]
	for item in trys:
		flag=0
		if(item.lower() != word.lower()):
			if(len(word)!=len(item)):
				flag=1
			for letter in item.lower():
				if (letter not in word.lower()):
					flag=1
				if(letter in word.lower()):
					if(item.lower().count(letter) != word.lower().count(letter)):
						flag=1
			if(flag==0):
				anagrams.append(item)
	return anagrams

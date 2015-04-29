
def word_count(subject):
	subject = subject.lower()
	temp = subject.split(' ')
	listi = {}

	for word in temp:
		temp[temp.index(word)] = word.replace(',','').replace('!','').replace('&','').replace('@','').replace('$','').replace('%','').replace('^','').replace(':','')
	
	for i in range(0 , temp.count('')):
		temp.remove('')
	
	for word in temp:
		if word not in listi  :
			count = 0
			for item in temp:
				if item == word:
					count += 1
			listi[word] = count
	return listi

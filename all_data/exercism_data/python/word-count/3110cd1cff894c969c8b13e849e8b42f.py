#!/usr/bin/python -tt

def word_count(string):

	dict_out={}
	string = string.replace('\n',' ') #replace new lines with spaces
	
	def fetch_word(string): #fetch first word in string stopping at first space
		out = ''
		l = 0
		for x in string:
			if x != ' ':
				out = out+x
				l+=1
			else:
				break
		return out,l
	
	def add_word(word): #add word to dictionary or increment counter
		if word not in dict_out:
			dict_out[word] = 1
		else:
			dict_out[word] += 1
			
	
	i=0
	e=len(string)
	
	while i<e:
		if string[i] == ' ': #skip all spaces
			i+=1
		else:
			word,l = fetch_word(string[i:])
			add_word(word)
			i+=l
			
	return dict_out

import re 

word_tuple =[]

def big_tuple(string):
	string = re.sub('[\n]',' ',string)
	word_tuple = []
	i=0
	string += ' ' #this adds a trailing space at the end of the string
	while i < len(string):
		space_place = re.search('  *', string).span()
		word_tuple.append((string[0:space_place[0]], 1))
		string = string[space_place[1]:len(string)]
	return word_tuple

		
def reduction_tuple(word_tuple):
	k = 1
	j = 0
	while j < len(word_tuple)-1 and k < len(word_tuple): 
		if word_tuple[k][0] == word_tuple[j][0]:
			del word_tuple[k]
			lis = list(word_tuple[j])
			lis[1] += 1
			del word_tuple[j]
			word_tuple.insert(j, tuple(lis))
		elif k + 1 < len(word_tuple) :  
			k += 1
		else:
			j += 1
			k = j +1
	return word_tuple
		
    
def word_count(str):
	return dict(reduction_tuple(big_tuple(str)))

# Python Exercism #5 Hamming


def distance(str1, str2):
    if len(str1) - len(str2):
        return "Error: strands are of different length"
    else: 
    	hamming_score = 0
    	for x,y in zip(str1,str2): 
            if x != y:
		hamming_score += 1
	return hamming_score
    

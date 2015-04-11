# calculates the Hamming distance between the two given strings. 
# if the lengths of the strings differ, all additional characters are counted towards the Hamming distance.
# both parameters must be indexable and have a defined __len__ method
def hamming(string1, string2):
	ham = 0
	for i in range(min(len(string1),len(string2))):
		if string1[i]!=string2[i]:
			ham+=1
	return ham+abs(len(string1)-len(string2))

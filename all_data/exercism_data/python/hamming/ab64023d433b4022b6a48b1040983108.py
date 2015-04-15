# hamming.py

def hamming(string1,string2):
	
	# shortcut cases
	if len(string1) == 0 and len(string2)==0:
		return 0
	if string1 == string2:
		return 0
		
	diffs = 0
	for i in range(max(len(string1), len(string2))):
		if i > len(string1) - 1:
			diffs = diffs +1
			continue
		if i > len(string2) - 1:
			diffs = diffs +1
			continue
			
		if string1[i] != string2[i]:
			diffs=diffs+1
	
	return diffs

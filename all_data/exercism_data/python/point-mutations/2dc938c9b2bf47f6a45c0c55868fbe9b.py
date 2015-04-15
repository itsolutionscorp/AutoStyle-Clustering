def hamming_distance(str1,str2):
	if len(str1)==0 and len(str2)==0:
		return 0

	result=0
	for i in range(min(len(str2),len(str1))):
		if str1[i]!=str2[i]:
			result+=1
	#result = abs(len(str2)-len(str1))+result

	return result

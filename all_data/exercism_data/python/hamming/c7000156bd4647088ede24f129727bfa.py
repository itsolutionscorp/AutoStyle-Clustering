def hamming(str1,str2):
	count=0
        extra=0
        if len(str1)!=len(str2):
        	if len(str1)>len(str2):
                	extra=len(str1)-len(str2)
                else:
                	extra=len(str2)-len(str1)
        	

	for u,v in zip(str1,str2):
		if u!=v:
                	count+=1
        return count+extra 

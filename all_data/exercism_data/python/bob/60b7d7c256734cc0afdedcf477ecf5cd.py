def hey(string):
	flag=1
        if len(string.strip())==0:
           return "Fine. Be that way!" 

	words=string.split()
	for word in words:
	  if word.isupper()==False:
		 flag=0
		 break
		 pass
	  pass
	if flag==1:
		return "Whoa, chill out!"
		pass
        else:
           if  string.endswith('?'):
             return "Sure."
             pass
           else:
             return "Whatever."
             pass
           pass

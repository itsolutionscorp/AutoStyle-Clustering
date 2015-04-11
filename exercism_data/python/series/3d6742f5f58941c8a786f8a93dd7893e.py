def slices(string,num):
	string=string
	num = num
	p=0
	lenstr = len(string)
	lists=[]
	#print lenstr,num
	if lenstr < num or num <1:
		raise ValueError, "length given is too long and cannot be nil"
		return
	while (num <= lenstr): 
		lists.append(innerlist(string[p:num]))
		p+=1
		num+=1
	#print lists
	return lists

def innerlist(string):
	string=string
	l=[]
	for i in string:
		l.append(int(i))
	return l

def verse(n):
	a,b,c,d = count(n)
	a_l = a.lower()
	b_l = b.lower()
	bott = bot(c)
	bott_l = bot(d)
	first = a + " "+ bott + " on the wall, " + a_l +" "+ bott + ".\n"
	second = act(n)+b_l+" " + bott_l + " on the wall.\n"
	return first+second

def count(n):
	if n > 2:
		return [str(n),str(n-1),'s','s']
	if n == 2:
		return [str(n),str(n-1),'s','']
	if n == 1:
		return [str(1),"No more",'','s']
	if n == 0:
		return ["No more",str(99),'s','s']

def bot(mul):
	return "bottle"+mul+" of beer"

def act(n):
	if n == 0:
		return "Go to the store and buy some more, "
	else:
		return "Take "+"it "*(n==1) + "one "*(n>1) +"down and pass it around, "


def song(start,end=0):
	return '\n'.join(verse(i) for i in range(start,end-1,-1))+'\n'

	

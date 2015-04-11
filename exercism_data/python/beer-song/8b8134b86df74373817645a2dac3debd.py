lyric = [" bottles of beer on the wall, ",
			" bottles of beer.\n",
			"Take one down and pass it around, ",
			" bottles of beer on the wall.\n"]

def verse(n):
	n=n
	if n>2:
		p1=str(n)+lyric[0]
		p2=str(n)+lyric[1]
		p3=lyric[2]
		p4=str(n-1)+lyric[3]
		#print p1+p2+p3+p4
		return p1+p2+p3+p4
	if n==1 :
		return "1 bottle of beer on the wall, 1 bottle of beer.\n""Take it down and pass it around, no more bottles of beer on the wall.\n"
	if n==0 :
		return "No more bottles of beer on the wall, no more bottles of beer.\n""Go to the store and buy some more, 99 bottles of beer on the wall.\n"
	if n==2:
		return "2 bottles of beer on the wall, 2 bottles of beer.\n""Take one down and pass it around, 1 bottle of beer on the wall.\n"

def song(k,l=0):
	if l==0:
		result=''
		for i in range(k,-1,-1):
			result +=(verse(i))
			result +='\n'
		return result
	else:
		#print l
		result=''
		for i in range(k,l-1,-1):
			result +=(verse(i))
			result +='\n'
		return result


	
	return 

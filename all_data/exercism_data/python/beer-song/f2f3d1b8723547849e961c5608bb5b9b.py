def _number_string(n,capitalize=False):
	if n==0:
		return "%so more bottles"%('N' if capitalize else 'n')
	if n==1:
		return "1 bottle"
	return "%s bottles"%n

def verse(n):
	return "%(number1)s of beer on the wall, %(number2)s of beer.\n%(takeone)s, %(number3)s of beer on the wall.\n"%{"number1":_number_string(n,True), 
		"number2":_number_string(n), "number3":_number_string((n-1)%100),
		"takeone": "Go to the store and buy some more" if n==0 else ("Take %s down and pass it around"%("it" if n==1 else "one"))
		}

def song(start,finish=0):
	answer=""
	for i in range(start,finish-1,-1):
		answer+=verse(i)+"\n"
	return answer

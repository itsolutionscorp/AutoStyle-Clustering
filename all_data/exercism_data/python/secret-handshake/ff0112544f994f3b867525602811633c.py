# changes number into an int
# treats strings as binary
def _numerize(number):
	n=-1
	if isinstance(number,str):
		try:
			n=int(number,2)
		except ValueError:
			n=0
	elif isinstance(number,int):
		n=number
	if n==-1:
		return 0
	if n<0 or n>31:
		return 0
	return n
	
_codes=['wink','double blink','close your eyes','jump']

# returns the handshake code for the given binary number.
# treats negative numbers or strings not representing binary numbers as 0
# only works for numbers in range(32), except for 16, 17, 18, 20 and 26, as these would be ambiguous results.
def handshake(number):
	n=_numerize(number)
	answer=[]
	for i in range(len(_codes)):
		if n & (1<<i):
			answer+=[_codes[i]]
	if n & (1<<len(_codes)):
		answer=list(reversed(answer))
	return answer

# translates a handshake back to an int. returns 0 if any element of handshake doesn't appear in _codes
# returns 0 for lists of actions in incoherent order
# only works for numbers in range(32), except for 16, 17, 18, 20 and 26, as these would be ambiguous results.
def _int_of(handshake):
	try:
		numbers = [ 1<<_codes.index(act) for act in handshake ]
	except ValueError:
		return 0
	if numbers == sorted(numbers):
		return sum(numbers)
	if sorted(numbers) == list(reversed(numbers)):
		return sum(numbers)+(1<<len(_codes))
	return 0

# translates a handshake back to a binary str. raises an exception if any element of handshake doesn't appear in _codes
# returns '0' for lists of actions in incoherent order
# only works for numbers in range(32), except for 16, 17, 18, 20 and 26, as these would be ambiguous results.
def code(handshake):
	return bin(_int_of(handshake))[2:]

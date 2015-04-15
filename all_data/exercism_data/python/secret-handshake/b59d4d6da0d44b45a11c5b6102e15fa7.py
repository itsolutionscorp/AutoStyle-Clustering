# Define a common function to avoid awkward code reuse
def _codeList():
	return 	['wink' ,'double blink','close your eyes','jump']

def code(n):
	cL = _codeList()

	# Catch poseurs doing things not in the secret handshake
	if any(i not in cL for i in n): return '0'
	if len(n) == 0: return ''
	revers = 0
	# Exploiting iteration along if statements
	if len(n) > 1 and cL.index(n[1]) < cL.index(n[0]):
			revers = 1
	returnVals='1'*revers + ''.join('1' if i in n else '0' for i in cL[::-1])
	return returnVals.lstrip('0')

def handshake(t):
	cL = _codeList()
	# Convert to string if necessary
	n = str(bin(t)[2:])if type(t) == int else t
	# Catch fake inputs (or too big of code).
	if any(i not in '01' for i in n):
		return []
	# Hack the code
	cS = '0'*(5-len(n))+n
	# Avoiding importing itertools
	coded = [cL[0]]*int(cS[4]) + \
		[cL[1]]*int(cS[3]) + \
		[cL[2]]*int(cS[2]) + \
		[cL[3]]*int(cS[1])
	# Reversing as needed
	coded = coded[::(-1)**int(cS[0])]
	return coded
			

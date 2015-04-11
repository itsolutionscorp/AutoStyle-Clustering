def handshake(code):
	decoder = {1:"wink",10:"double blink",100:"close your eyes",1000:"jump",10000:'r'}
	res = []
	if isinstance(code,int):
		if code < 0:
			return []
		code = str(bin(code)[2:])
	y = len(code)
	for i in range(y-1,-1,-1):
		if code[i] not in '01':
			return []
		if int(code[i:]) in decoder:
			print decoder[int(code[i:])]
			res.append(decoder[int(code[i:])])
		code = str(int(code)-int(code[i:]))
	if res[-1] == 'r':
		res = res[-2::-1]
	return res
	
		
		
def code(shake):
	coder = {"wink":1,"double blink":10,"close your eyes":100,"jump":1000}
	used = []
	res = 0
	for move in shake:
		if move not in coder or move in used:
			return '0'
		res+=coder[move]
	if shake == handshake(str(res)):
		return str(res)
	return str(res+10000)
	
		
	

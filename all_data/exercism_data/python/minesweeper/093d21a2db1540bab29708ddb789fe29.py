def lookAt(x):
	if x == "*":
		return ''
	else:
		return 1

def board(inp):
	#Error Checking
	n = len(inp)
	m = len(inp[0])
	border = inp[0]
	for i in inp:
		for j in i:
			if j not in ['+',' ','|','-','*'] or len(i)!=m or border != inp[-1]:
				raise ValueError("Malformed board 1")
	inpIn = [i[1:-1]for i in inp[1:-1]]
	inpBoard = [list(i.replace(' ','0')) for i in inpIn]
	for i in inpBoard:
		for j,k in enumerate(i):
			if k =='0':
				i[j] = 0
	for i in range(n-2):
		for j in range(m-2):
			if inpIn[i][j] == '*':
				ir = list(set([max(i-1,0),i,i+1]))
				ir.sort()
				jr = list(set([max(j-1,0),j,j+1]))
				jr.sort()
				for ii in ir:
					for jj in jr:
						try:
							inpBoard[ii][jj] += lookAt(inpBoard[ii][jj])
						except IndexError:
							continue
	outp = [border]
	for i in range(1,n-1):
		outp.append(''.join(["|"] + [' ' if j == 0 else str(j) for j in inpBoard[i-1]]+["|"]))
	outp += [border]
	return outp

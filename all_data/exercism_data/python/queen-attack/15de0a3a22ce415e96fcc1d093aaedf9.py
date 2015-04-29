def board(w, b):
	res = ['________' for i in range(8)]
	if w == b:
		raise ValueError('same position')
	test = [w[0],w[1],b[0],b[1]]
	for i in test:
		if i not in range(0,8):
			raise ValueError('invalid position')
	res[w[0]] = ''.join(['_' if i != w[1] else 'W' for i in range(8)])
	res[b[0]] = ''.join(['_' if i != b[1] else 'B' for i in range(8)])
	return res


def can_attack(w,b):
	diags = []
	if w == b:
		raise ValueError('same position')
	test = [w[0],w[1],b[0],b[1]]
	for i in test:
		if i not in range(0,8):
			raise ValueError('invalid position')
	if w[0] == b[0] or w[1] == b[1]:
		return True
	for i in range(8):
		x_diag = (i,(i-w[0])+w[1])
		diags.append(x_diag)
		y_diag = (i,w[1]+(w[0]-i))
		diags.append(y_diag)
	if b in diags:
		return True
	return False

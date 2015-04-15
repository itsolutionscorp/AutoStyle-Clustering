gesture = {
	0: 'wink',
	1: 'double blink',
	2: 'close your eyes',
	3: 'jump'
}

codepoints = {val: key for key, val in gesture.items()}

def handshake(code):
	if isinstance(code, int) and code >= 0:
		steps = bin(code)[-1:1:-1]
	elif isinstance(code, str) and all(char in "01" for char in code):
		steps = code[::-1]
	else:
		return []

	ones_pos = [pos for pos, step in enumerate(steps) if step == '1']
	gestures = [gesture.get(pos) for pos in ones_pos if pos < 4]
	if 4 in ones_pos:
		gestures.reverse()
	return gestures

def code(handshake):
	if any(gesture not in codepoints for gesture in handshake):
		return '0'

	ones_pos = [codepoints[gesture] for gesture in handshake]
	if len(ones_pos) > 1 and ones_pos[0] > ones_pos[1]:
		ones_pos.append(4)
	ones_pos.sort()

	code = []
	for pos in ones_pos:
		code += ['0'] * (pos - len(code)) + ['1']
	code.reverse()
	
	if code:
		return "".join(code)
	else:
		return '0'

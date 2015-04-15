actions = {1:'wink', 2:'double blink', 4:'close your eyes', 8:'jump'}
codes = {v:k for k,v in actions.items()}
A = len(actions)

def handshake(n):
	try:
		n = int(n, 2)
	except (TypeError, ValueError):
		pass
	try:
		if not 0 < n < 2 ** (A + 1):
			return []
		shake = [actions[2 ** i] for i in range(A) if 2 ** i & n]
		if 2 ** A & n:
			shake.reverse()
		return shake
	except ValueError:
		return []
	
def code(shake):
	try:
		num = sum([codes[s] for s in shake])
		if len(shake) > 1:
			if codes[shake[0]] > codes[shake[1]]:
				num += 2 ** A
	except KeyError:
		num = 0
	return "{0:b}".format(num)

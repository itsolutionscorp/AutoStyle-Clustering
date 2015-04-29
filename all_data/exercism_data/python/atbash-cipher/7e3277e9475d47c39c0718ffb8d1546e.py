import string

tr = dict(zip(string.ascii_lowercase, reversed(string.ascii_lowercase)))
tr.update(zip(string.digits, string.digits))
tr.update({' ': ''})

def encode(s):
	s = s.lower()
	res = []
	i = 0 # group separator count
	for c in s:
		if c.isalpha() or c.isdigit():
			res.append(tr[c])
			if (len(res) - i) % 5 == 0:
				res.append(' ')
				i += 1
	return "".join(res).rstrip()

def decode(s):
	return "".join(tr[x] for x in s)

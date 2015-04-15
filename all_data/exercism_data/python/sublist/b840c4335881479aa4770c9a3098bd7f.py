SUBLIST = 0
SUPERLIST = 1
EQUAL = 2
UNEQUAL = 3

def _match_sublist(n, m, ii):
	try:
		for i in xrange(ii, ii + len(m)):
			if n[i] != m[i - ii]:
				return False
		return True
	except IndexError:
		return False

def check_lists(m, n):
	CONTAINS = SUPERLIST if len(m) > len(n) else SUBLIST
	if len(m) > len(n):
		m, n = n, m # make sure m is the shorter list
	if len(m) == 0:
		return EQUAL if len(n) == 0 else CONTAINS
	m0 = m[0]
	for i in xrange(len(n)):
		if n[i] == m0 and _match_sublist(n, m, i):
			return EQUAL if len(m) == len(n) else CONTAINS
	return UNEQUAL

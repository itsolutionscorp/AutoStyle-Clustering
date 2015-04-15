import re

alp = [x for x in "abcdefghijklmnopqrstuvwxyz1234567890"]
pla = [x for x in "zyxwvutsrqponmlkjihgfedcba1234567890"]

def encode(s):
	r = [pla[alp.index(x.lower())] for x in s if re.search(r"[a-z0-9]",x.lower()) is not None]
	return ' '.join([''.join(r[x:x+5]) for x in range(0,len(r),5)])

def decode(s):
	return ''.join([alp[pla.index(x.lower())] for x in s if re.search(r"[a-z0-9]",x.lower()) is not None])

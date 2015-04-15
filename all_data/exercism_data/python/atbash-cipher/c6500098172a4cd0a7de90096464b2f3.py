import re

alp = strings.ascii_lowercase+strings.digits
pla = reversed(strings.ascii_lowercase)+strings.digits

def encode(s):
	r = [pla[alp.index(x.lower())] for x in s if x.lower() in alp]
	return ' '.join([''.join(r[x:x+5]) for x in range(0,len(r),5)])

def decode(s):
	return ''.join([alp[pla.index(x.lower())] for x in s if x.lower() in alp])

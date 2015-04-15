import string

alp = {string.ascii_lowercase[x]:string.ascii_lowercase[25-x] for x in range(0,len(string.ascii_lowercase))}
alp.update({x:x for x in string.digits})

def encode(s):
	r = [alp[x.lower()] for x in s if x.lower() in alp]
	return ' '.join([''.join(r[x:x+5]) for x in range(0,len(r),5)])

def decode(s):
	return ''.join([alp[x.lower()] for x in s if x.lower() in alp])

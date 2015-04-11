def decode(string):
	return flip(string.lower())

def encode(string):
	str2 = flip(string.lower())
	rang = range(int(len(str2)/5)+1)
	str3 =' '.join([str2[k*5:min(5+k*5,len(str2))] for k in rang]).strip()
	# str3 = ' '.join([i for i in str3 ).strip()
	return str3


def flip(string):
	cipher = 'abcdefghijklmnopqrstuvwxyz'
	nums = '0123456789'
	str2 =  [cipher[25-cipher.index(i)] if (i in cipher) else i for i in string]
	return ''.join([i for i in str2 if i in cipher or i in nums])

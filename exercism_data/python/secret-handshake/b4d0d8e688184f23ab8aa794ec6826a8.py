actions = ('wink','double blink','close your eyes','jump')

def handshake(hcode):
	''' create secret handshake from numeric or binary input '''
	if isinstance(hcode, int):
		# convert positive integer to binary string
		if not hcode >= 0:
			return []
		hcode = '{:b}'.format(hcode)
	hcode = hcode.rjust(1+len(actions),'0') # pad to full width
	if len(hcode) > (1+len(actions)):
		return []
	if set(hcode) - set('01'):
		return []
	shake = [action for ii,action in enumerate(actions) if hcode[-ii-1]=='1']
	if hcode[0]=='1':
		shake = shake[-1::-1]
	return shake

def code(hshake):
	''' identify binary code corresponding to secret handshake '''
	if set(hshake) - set(actions):
		return '0'
	d = dict((a,2**i) for i,a in enumerate(actions))
	code_vals = [d[h] for h in hshake]
	hcode = '{0:b}'.format(sum(code_vals))
	if sorted(code_vals) != code_vals:
		hcode = '1' + hcode
	return hcode

if __name__ == '__main__':
    unittest.main()

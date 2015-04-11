'''
encode message using a square code
'''

from math import ceil

def encode(msg):
	'''
	encode message using square code
	'''
	msg = ''.join([ch for ch in msg.lower() if ch.isalnum()])
	rowlen = int(ceil(len(msg)**0.5))
	# encode
	code = ''.join([msg[ii::rowlen] for ii in range(rowlen)])
	# break into units of 5 chars for display
	code = ' '.join([code[5*jj:5*jj+5] for jj in range(ceil(len(code)/5))])
	return code

def decode(ciph):
	'''
	decode square-coded message
	'''
	ciphlist = list(''.join([ch for ch in ciph if ch.isalnum()]))
	ciphlen = len(ciphlist)
	rowlen = int(ceil(ciphlen**0.5))
	longcollen = int(ceil(ciphlen/rowlen))
	nspaces = rowlen * longcollen - ciphlen
	msglist = ['*']*ciphlen
	# add in spaces at appropriate places to make len(ciphlist) be n*rowlen
	for n in range(rowlen-nspaces+1,rowlen+1):
		ciphlist.insert(n*longcollen-1,' ')
	# decode
	for r in range(longcollen):
		msglist[(r*rowlen):((r+1)*rowlen)] = ciphlist[r::longcollen]
	return ''.join(msglist).strip()

if __name__=='__main__':
	msg = "Be who you are and say what you feel, because those who mind "\
	      "don't matter and those who matter don't mind."
	ciph = encode(msg)
	print('encoded message: ',ciph)
	print('decoded cipher: ',decode(ciph))
	ciph = 'betcw tttne ayahm htdwn ouoao ehdus mtsro sfeit edyae tnewo '\
	       'oyehd rhnuw lodao tahbs onmmr aeend ai'
	print('decoded cipher: ',decode(ciph))

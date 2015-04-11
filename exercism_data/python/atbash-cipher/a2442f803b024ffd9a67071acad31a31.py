from string import *

base_alpha = lowercase
invr_alpha = base_alpha[::-1]
base_alpha = base_alpha + digits
invr_alpha = invr_alpha + digits

def encode(plain_text):
	plain_text = lower(plain_text)
	plain_text = replace(plain_text,' ','')
	print 'plain = ',plain_text
	encoded_text = ''
	cnt = 0
	for x in plain_text:
		if (x not in base_alpha):
			continue
		cnt += 1
		i = find(base_alpha,x)
		encoded_text += invr_alpha[i]
		if ((cnt % 5) == 0):
			encoded_text += ' '
	if (encoded_text[-1] == ' '):
		encoded_text = 	encoded_text[:-1]
	return encoded_text
	
	
def decode(coded_text):
	coded_text = replace(coded_text,' ','')
	decoded_text = ''
	for x in coded_text:
		i = find(base_alpha,x)
		decoded_text += invr_alpha[i]
	return decoded_text
	

import string

def make_cipher(flag):
	cipher = {}
	inputs = list(string.ascii_lowercase)
	outputs = list(string.ascii_lowercase)
	if flag:
		outputs.reverse()
	else:
		inputs.reverse()
	nums = range(10)
	[cipher.update({str(nums[i]):str(nums[i])}) for i in nums]
	[cipher.update({inputs[i]:outputs[i]}) for i in range(len(inputs))]
	return cipher

def decode(code):
	cipher = make_cipher(False)
	code = code.replace(' ','')
	code =''.join([cipher[code[i]] for i in range(len(code))])
	return code 
	
def encode(code):
	code = code.lower().replace(' ','')
	exclude = set(string.punctuation)
	code = ''.join(ch for ch in code if ch not in exclude)
	cipher = make_cipher(True)
	code =''.join([cipher[code[i]] for i in range(len(code))])
	temp = []
	for i in range(0,len(code),5):
		temp.append(code[i:i+5])
	code = ' '.join(temp)
	return code

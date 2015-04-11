def decode(string):
	string = ''.join(ch for ch in string.lower() if ch.isalnum())
	return_str = ''
	for x in string:
		if x.isalpha():
			25 - ord('a') + 97
			char = chr(25 - ord(x) + 97 + ord('a'))
			return_str += char
		else:
			return_str += x
	return return_str

def encode(string): 
	string = decode(string)
	str_list = []
	while len(string) > 5:
		str_list.append(string[:5])
		string = string[5:]
	if string:
		str_list.append(string)
	return ' '.join(str_list)

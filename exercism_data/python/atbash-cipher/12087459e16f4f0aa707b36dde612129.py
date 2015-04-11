import string

cipher = {string.ascii_lowercase[i]:string.ascii_lowercase[25-i] for i in range(0, 26)}
cipher[","] = ""
cipher["."] = ""


def  translate_text(message, type):
	translated_text= []
	message = message.lower()
	message = message.replace(" ", "")
	for i in message:
		try:
			translated_text.append(cipher[i])
		except KeyError:
			translated_text.append(i)
	formated_text = ''.join(translated_text)
	if type == "decode":
		return formated_text
	elif type == "encode":
		return ' '.join([formated_text[p:p+5] for p in range(0, len(formated_text), 5)])
	

def encode(message):
	return translate_text(message, 'encode')

def decode(message):
	return translate_text(message, 'decode')

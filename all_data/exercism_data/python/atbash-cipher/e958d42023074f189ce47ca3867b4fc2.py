from string import maketrans 

intab = "abcdefghijklmnopqrstuvwxyz"
outtab = intab[::-1]

def decode(text):
	text = "".join(text.split())
	text = text.lower()
	trantab = maketrans(intab, outtab)
	return remove_tokens(text.translate(trantab)).strip()


def encode(text):
	text = "".join(text.split())
	text = text.lower()
	trantab = maketrans(outtab, intab)
	return grouping(remove_tokens(text.translate(trantab))).strip()


def grouping(text):
	sequence = ""
	for x in range(len(text)):
		if x % 5 == 0:
			sequence += " "
			sequence += text[x]
		else:
			sequence += text[x]
	return sequence

def remove_tokens(text):
	return text.replace(',', '').replace('.', '').replace(' ', '')

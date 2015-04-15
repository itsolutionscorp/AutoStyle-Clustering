from math import sqrt, ceil

# strips extraneous characters (punctuation, etc) from text
def _strip_extraneous(text):
	answer=''
	for c in text:
		if c.isalpha() or c.isdigit():
			answer+=c
	return answer

# breaks text into a square for use in the square cipher
def _break_into_lines(text):
	size=ceil(sqrt(len(text)))
	answer=['']
	for c in text.lower():
		if len(answer[-1])>=size:
			answer.append('')
		answer[-1]+=c
	return answer

# inserts a space every (n+1)st character
def _insert_spaces(text, n=5):
	answer=['']
	for c in text:
		if len(answer[-1])>=5:
			answer.append('')
		answer[-1]+=c
	return ' '.join(answer)

# encodes text using a square cipher
def square_cipher(text):
	lines=_break_into_lines(_strip_extraneous(text))
	answer=''
	for i in range(len(lines[0])):
		for j in range(len(lines)):
			if i<len(lines[j]):
				answer+=lines[j][i]
	return answer
	
# encodes text using a square cipher and inserts spaces
def encode(text):
	return _insert_spaces(square_cipher(text))

# decodes text from a square cipher
def decode(text):
	source=_strip_extraneous(text)
	size=ceil(sqrt(len(source)))
	height=ceil(len(source)/size)
	padding=size*height-len(source)
	answer=['']*height
	pos=0
	for c in source:
		answer[pos]+=c
		pos+=1
		if len(answer[-1])+padding>=size and pos==len(answer)-1:
			pos+=1
		pos=pos%height
	return ''.join(answer)

_alphabet='abcdefghijklmnopqrstuvwxyz'
_atbash_map=str.maketrans(_alphabet,''.join(list(reversed(_alphabet))))

# encodes/decodes single character c using the atbash cipher. retains numerals.
def atbash(c):
	if c.isdigit():
		return c
	elif c.isalpha():
		return c.lower().translate(_atbash_map)
	return ''

# decodes text from the atbash cipher, retaining numerals and removing spaces
def decode(text):
	answer=''
	for c in text:
		answer+=atbash(c)
	return answer

# encodes text using the atbash cipher, retaining numerals and inserting a space every 5 characters
def encode(text):
	answer=['']
	for c in text:
		d=atbash(c)
		if len(d)>0 and len(answer[-1])>=5:
			answer.append(d)
		else:
			answer[-1]+=d
	return ' '.join(answer)

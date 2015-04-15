from math import sqrt, ceil

def encode(phrase):
	stripped = ''
	for i in range(0,len(phrase)):
		if phrase[i].lower().isalnum():
			stripped += phrase[i].lower()
	finalString = ''
	if len(stripped) > 0:
		width = int(ceil(sqrt(len(stripped))))
		length = int(ceil(float(len(stripped))/float(width)))
		count = 0
		for m in range(0,width):
			for n in range(0,length):
				if (m+n*width < len(stripped)):
					if count%5 == 0 and count > 1:
						finalString += ' '
					finalString += stripped[m+n*width]
					count += 1
	return finalString


def decode(gibberish):
	stripped = ''
	for i in range(0,len(gibberish)):
		if gibberish[i].lower().isalnum():
			stripped += gibberish[i].lower()
	finalString = ''
	if len(stripped) > 0:
		width = int(ceil(sqrt(len(stripped))))
		length = int(ceil(float(len(stripped))/float(width)))
		gap = length*width - len(stripped)
		for m in range(0,length):
			for n in range(0,width):
				offset = 0
				if n > width - gap:
					offset = n + gap - width
				if (m+n*length-offset < len(stripped)) and not (m == length-1 and n > length - gap):
					finalString += stripped[m+n*length-offset]
	return finalString

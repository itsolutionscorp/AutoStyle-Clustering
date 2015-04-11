import re
import math

def encode(text):
	text = re.sub(r'[\W]+', '', text.lower())
	print text
	result = '' 

	len_side =  int(math.ceil(math.sqrt(len(text))))

	for col_num in range(0, len_side):
		n = 0
		while len(text) / len_side >= n:
			try:
				result += text[ n*len_side+col_num ]
			except IndexError:
				result += ''
			n += 1
		result += ' '
	return result.strip()

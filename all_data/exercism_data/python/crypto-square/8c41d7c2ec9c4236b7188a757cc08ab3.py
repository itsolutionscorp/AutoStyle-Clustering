def encode(text):
	text = ''.join([x.lower() for x in text if x.isalnum()])
	square = len(text)
	while square ** .5 != int(square ** .5):
		square += 1
	line_length = int(square ** .5)
	print line_length
	temp = []
	while len(text) > line_length:
		piece, rest = text[0:line_length],text[line_length:]
		text = rest
		temp.append(piece)
	filler = '*' * (line_length - len(text))
	text = text + filler
	temp.append(text)
	res = []
	for x in range(line_length):
		res_word = ''
		for y in temp:
			if y[x] != '*':
				res_word += y[x]
		res.append(res_word)
	res = ' '.join(res)
	return res

print encode('abcdefghi')

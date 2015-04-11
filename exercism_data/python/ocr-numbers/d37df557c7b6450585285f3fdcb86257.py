def grid(s):
	return _join_pipe_letters(_letter_to_pipeletter[c] for c in s)

def number(lines):
	if len(lines) != 4 or len(set(map(len, lines))) != 1 or len(lines[0]) % 3 != 0:
		raise ValueError("Incorrect pipe-font string")
	return "".join(_pipeletter_to_letter.get(t, "?") for t in _split_pipe_letters(lines))

def _join_pipe_letters(pipe_letters):
	""" Join a sequence of pipe-font digits into a single pipe-font 'string' """
	return list(map("".join, zip(*pipe_letters)))

def _split_pipe_letters(lines):
	""" Split a 'string' written in pipe-font into separate pipe-font digits """
	for i in range(0, len(lines[0]), 3):
		yield (	lines[0][i:i+3],
				lines[1][i:i+3],
				lines[2][i:i+3],
				lines[3][i:i+3])


_alphabet = [
	" _     _  _     _  _  _  _  _ ",
	"| |  | _| _||_||_ |_   ||_||_|",
	"|_|  ||_  _|  | _||_|  ||_| _|",
	"                              ",
]

_pipeletter_to_letter = {}
_letter_to_pipeletter = {}
for i, t in enumerate(_split_pipe_letters(_alphabet)):
	_pipeletter_to_letter[t] = str(i)
	_letter_to_pipeletter[str(i)] = t

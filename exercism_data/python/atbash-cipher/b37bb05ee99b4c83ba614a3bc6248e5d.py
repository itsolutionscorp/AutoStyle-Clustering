def encode(x):
	y = ""
	counter = 0
	z = ""
	cipher = {
	"a": "z",
	"b": "y",
	"c": "x",
	"d": "w",
	"e": "v",
	"f": "u",
	"g": "t",
	"h": "s",
	"i": "r",
	"j": "q",
	"k": "p",
	"l": "o",
	"m": "n",
	"n": "m",
	"o": "l",
	"p": "k",
	"q": "j",
	"r": "i",
	"s": "h",
	"t": "g",
	"u": "f",
	"v": "e",
	"w": "d",
	"x": "c",
	"y": "b",
	"z": "a",
	" ": " ",
	"1": "1",
	"2": "2",
	"3": "3",
	"4": "4", 
	"5": "5",
	"6": "6",
	"7": "7",
	"8": "8",
	"9": "9",
	}

	for i in x:
		if i.isalnum():
			y += i.lower()
			if counter == 4:
				y += " "
				counter = 0
			else:
				counter += 1

	for i in y:
		z += cipher[i]

	return z.strip()


def decode(x):
	cipher = {
	"a": "z",
	"b": "y",
	"c": "x",
	"d": "w",
	"e": "v",
	"f": "u",
	"g": "t",
	"h": "s",
	"i": "r",
	"j": "q",
	"k": "p",
	"l": "o",
	"m": "n",
	"n": "m",
	"o": "l",
	"p": "k",
	"q": "j",
	"r": "i",
	"s": "h",
	"t": "g",
	"u": "f",
	"v": "e",
	"w": "d",
	"x": "c",
	"y": "b",
	"z": "a",
	" ": "",
	"1": "1",
	"2": "2",
	"3": "3",
	"4": "4", 
	"5": "5",
	"6": "6",
	"7": "7",
	"8": "8",
	"9": "9",
	}
	y = ""

	for i in x:

		y += cipher[i]

	return y

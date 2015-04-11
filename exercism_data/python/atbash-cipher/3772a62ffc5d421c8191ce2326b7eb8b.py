def encode(words):
	words = words.lower()
	plain = "abcdefghijklmnopqrstuvwxyz1234567890"
	cipher = "zyxwvutsrqponmlkjihgfedcba1234567890"
	encoded = ""
	count = 0
	for letter in words:
		position = plain.find(letter)
		if position != -1:
			if count % 5 == 0 and count != 0:
				encoded = encoded + " "
			encoded = encoded + cipher[plain.find(letter)]
			count = count + 1

	return encoded

def decode(words):
	words = words.lower()
	plain = "abcdefghijklmnopqrstuvwxyz1234567890"
	cipher = "zyxwvutsrqponmlkjihgfedcba1234567890"
	decoded = ""
	count = 0
	for letter in words:
		position = plain.find(letter)
		if position != -1:
			decoded = decoded + cipher[plain.find(letter)]

	return decoded

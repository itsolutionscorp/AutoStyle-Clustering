import re
import math

def encode(message):
	message = message.lower()
	message = re.sub("[^a-z0-9]", "", message)
	square = math.ceil(math.sqrt(len(message)))
	crypto = ""
	for i in range(square):
		j = 0
		if i > 0:
			crypto += " "
		while j + i < len(message):
			crypto += message[i + j]
			j += square
	return(crypto)

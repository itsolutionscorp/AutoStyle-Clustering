import math, re

def encode(message):
	# strip non-encodables from input
	message = re.sub('[^a-z0-9]+', '', message.lower())
	if not message:
		return ""

	# pad message up to the closest square length
	block_length = math.ceil(math.sqrt(len(message)))
	message = message.ljust(block_length**2, ' ')

	# split a message into lines, then read columns from resulting matrix
	rows = (message[i:i+block_length] for i in range(0, len(message), block_length))
	return " ".join("".join(column).strip() for column in zip(*rows))

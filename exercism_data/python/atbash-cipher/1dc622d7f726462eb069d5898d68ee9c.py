import re
from string import maketrans, translate, ascii_lowercase, join

cipher = maketrans(ascii_lowercase, join(reversed(ascii_lowercase), ''))
pattern = re.compile('[^a-zA-Z0-9]+', re.UNICODE)

def encode(message):
	message = translate(strip(message), cipher)
	message = join((message[i:i+5] for i in range(0, len(message), 5)), ' ')
	return message

def decode(message):
	return translate(strip(message), cipher)

def strip(message):
	return pattern.sub('' ,message.lower())

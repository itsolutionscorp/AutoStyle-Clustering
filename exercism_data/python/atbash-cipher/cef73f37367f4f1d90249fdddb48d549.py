import string
encodeDict = dict(zip(string.lowercase,reversed(string.lowercase)))
decodeDict = dict(zip(reversed(string.lowercase),string.lowercase))

for i in range(0,10):
	encodeDict[str(i)] = str(i)
	decodeDict[str(i)] = str(i)

def encode(decodedString):
	encodedString = ''.join(
		[encodeDict[char] for char in decodedString.lower() if char in encodeDict ]
	)
	return ''.join(
		[encodedString[i:i+5]+' ' for i in range(0,len(encodedString),5)]
	).strip()

def decode(encodedString):
	return ''.join(
		[decodeDict[char] for char in encodedString.lower() if char in decodeDict]
	).strip()

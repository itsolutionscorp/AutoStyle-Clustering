def encode(text):
	a = ''.join([chr(ord('z') + ord('a') - ord(t.lower())) if t.isalpha() else t for t in text if t.isalnum()])
	return ' '.join([a[i:i+5] for i in range(0, len(a), 5)])

def decode(text):
	return ''.join([chr(ord('z') + ord('a') - ord(t)) for t in text if t.islower()])

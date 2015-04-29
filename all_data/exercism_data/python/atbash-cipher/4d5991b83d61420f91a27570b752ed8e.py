class Atbash_cipher(object):
	def __init__(self):
		self.key = 'zyxwvutsrqponmlkjihgfedcba'
		self.book = 'abcdefghijklmnopqrstuvwxyz'
		self.encryption = zip(self.book, self.key)
		self.decryption = zip(self.key, self.book)
		self.translation = str.maketrans(self.book,self.key)
	def encode(self, message):
		message = message.lower()
		message = message.strip('@?.!/;:')
		message = message.replace(',', '')
		message = message.replace(' ', '')

		message = [message[k:k+5] for k in range(0, len(message), 5)]
		blank = " "
		message = blank.join(message)
		return message.translate(self.translation)
	def decode(self, message):
		message = message.replace(' ', '')
		return message.translate(self.translation)

_line1 = "{num_bottles_cap} of beer on the wall, {num_bottles_low} of beer.\n"
_line2 = "Take {quant} down and pass it around, {num_bottles} of beer on the wall.\n"
_line_empty = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

class Beer(object):
	def verse(self, num):
		quantity = ''
		if num == 0:
			quantity = 'no more bottles'
		elif num == 1:
			quantity = '1 bottle'
		else:
			quantity = str(num) + ' bottles'
		verse = _line1.format(num_bottles_cap=quantity.capitalize(), num_bottles_low=quantity)
		if num == 0:
			verse += _line_empty
			return verse
		if num == 1:
			verse += _line2.format(quant='it', num_bottles='no more bottles')
			return verse
		if num == 2:
			verse += _line2.format(quant='one', num_bottles='1 bottle')
			return verse
		verse += _line2.format(quant='one', num_bottles=str(num-1) + ' bottles')
		return verse

	def sing(self, hi, lo=0):
		song = ''
		for i in range(hi, lo-1, -1):
			song += self.verse(i) + '\n'
		return song

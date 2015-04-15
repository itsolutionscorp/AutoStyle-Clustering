s1 = "{0} bottle{1} of beer on the wall, {0} bottle{1} of beer.\n"
s2 = "Take one down and pass it around, {0} bottle{1} of beer on the wall.\n"
s_zero = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
s_one = "Take it down and pass it around, no more bottles of beer on the wall.\n"

class Beer:
	def verse(self, num):
		if num == 0:
			return ''.join([s1.format('no more', 's').capitalize(), s_zero])
		if num == 1:
			return ''.join([s1.format(1, ''), s_one])

		return ''.join([s1.format(num, 's'), s2.format(num-1, ('' if num == 2 else 's'))])

	def sing(self, s_num, f_num=0):
		return "\n".join([ self.verse(i) for i in range(s_num, f_num-1, -1) ]) + "\n"

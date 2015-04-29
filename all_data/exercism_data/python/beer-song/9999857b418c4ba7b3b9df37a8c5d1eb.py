class Beer(object):
	def __init__(self):
		self.beer = self

	def verse(self, num):
		bottles = {
		'plural': str(num) + " bottles",
		'single': str(num) + " bottle",
		'none': "No more bottles"
		}

		action = {
		'plural': 'one',
		'single': 'it'
		}
		
		remainder = {
		'plural': str(num-1) + " bottles",
		'single': str(num-1) + " bottle",
		'none': "no more bottles",
		'start_over': '99 bottles'
		}

		if num < 0:
			result = "Enter a positive number please"
			return SystemExit(result)

		if num > 2:
			bottles = bottles['plural']
			action = action['plural']
			remainder = remainder['plural']

		if num == 2:
			bottles = bottles['plural']
			action = action['plural']
			remainder = remainder['single']

		if num == 1:
			bottles = bottles['single']
			action = action['single']
			remainder = remainder['none']

		if num == 0:
			result = ("%s of beer on the wall, %s of beer.\n"
					"Go to the store and buy some more, "
					"%s of beer on the wall.\n") % (
					bottles['none'], bottles['none'].lower(), remainder['start_over']
					)
		else:
			result = ("%s of beer on the wall, %s of beer.\n"
					"Take %s down and pass it around, "
					"%s of beer on the wall.\n") %(bottles, bottles, action, remainder)

		
		return result

	def sing(self, start, end=0):	
		
		result = []
		num = start
		while num >= end:
			verse = self.verse(num)
			result.append(verse)
			result.append('\n')
			num -=1
		song = "".join(result)
		return song 

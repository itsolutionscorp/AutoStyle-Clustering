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

		if num > 2:
			result = 	("%s of beer on the wall, %s of beer.\n"
						"Take %s down and pass it around, " 
						"%s of beer on the wall.\n") % (
						bottles['plural'], bottles['plural'], action['plural'], remainder['plural']
						)

		elif num == 2:
			result = 	("%s of beer on the wall, %s of beer.\n"
						"Take %s down and pass it around, " 
						"%s of beer on the wall.\n") % (
						bottles['plural'], bottles['plural'], action['plural'], remainder['single']
						)					

		elif num == 1:
			result = 	("%s of beer on the wall, %s of beer.\n"
						"Take %s down and pass it around, "
						"%s of beer on the wall.\n") % (
						bottles['single'], bottles['single'], action['single'],remainder['none']
						)

		elif num == 0:
			result = 	("%s of beer on the wall, %s of beer.\n"
						"Go to the store and buy some more, "
						"%s of beer on the wall.\n") % (
						bottles['none'], bottles['none'].lower(), remainder['start_over']
						)
		
		else:
			result = "Enter a positive number please"
			raise SystemExit(result)
		
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

		
	


		






		

	

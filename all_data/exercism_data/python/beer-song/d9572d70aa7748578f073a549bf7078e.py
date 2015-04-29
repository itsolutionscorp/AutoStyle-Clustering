def capitaliseFirst(sentence):
	return sentence[0].upper() + sentence[1:]
	
def getNumberRep(number):
		if number == 0:
			return "no more"
		else:
			return number
			
def getBottlePlural(number):
	return "bottle" + "s" * (number != 1)

class Beer:
	verseChunk1 = "{0} {1} of beer on the wall, {0} {1} of beer.\n"
	verseChunk2 = "Take {2} down and pass it around, {0} {1} of beer on the wall.\n"
	verseChunk3 = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
		
	def verse(self, number):
		bottleItemWord = "one" if number > 1 else "it"
		
		totalVerse = self.verseChunk1.format(getNumberRep(number), getBottlePlural(number))
		totalVerse = capitaliseFirst(totalVerse)
		if (number > 0):
			totalVerse += self.verseChunk2.format(getNumberRep(number - 1), getBottlePlural(number - 1), bottleItemWord)
		else:
			totalVerse += self.verseChunk3
			
		return totalVerse
		
	def sing(self, start, end = 0):
		return '\n'.join(self.verse(n) for n in range(start, end - 1, -1)) + '\n'

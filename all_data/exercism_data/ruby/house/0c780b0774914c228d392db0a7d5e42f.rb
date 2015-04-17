class House

	ITEMS_ARRAY = [
		"the house that Jack built.\n",
		"the malt that lay in ",
		"the rat that ate ",
		"the cat that killed ",
		"the dog that worried ",
		"the cow with the crumpled horn that tossed ",
		"the maiden all forlorn that milked ",
		"the man all tattered and torn that kissed ",
		"the priest all shaven and shorn that married ",
		"the rooster that crowed in the morn that woke ",
		"the farmer sowing his corn that kept ",
		"the horse and the hound and the horn that belonged to "
	]

	def verse(verse_number)
		string = "This is "
		(verse_number-1).downto(0).each {|x| string += ITEMS_ARRAY[x]}
		string
	end

	def verses(start, stop)
		rhyme = ""
		start.upto(stop) {|verse| rhyme += verse(verse) + "\n"}
		rhyme
	end

end
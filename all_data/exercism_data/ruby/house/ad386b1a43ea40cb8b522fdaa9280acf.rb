class House

	POEM = [ ['stop', 'the horse and the hound and the horn'],
			 ['belonged to', 'the farmer sowing his corn'],
			 ['kept', 'the rooster that crowed in the morn'],
			 ['woke', 'the priest all shaven and shorn'],
			 ['married', 'the man all tattered and torn'],
			 ['kissed', 'the maiden all forlorn'],
			 ['milked', 'the cow with the crumpled horn'],
			 ['tossed', 'the dog'],
			 ['worried', 'the cat'],
			 ['killed', 'the rat'],
			 ['ate', 'the malt'],
			 ['lay in', 'the house that Jack built.'] ]

		 		 
	def self.recite
		this_line = 'This is ' + POEM.last[1]
		that_line = ''
		stanza = this_line
		poem = stanza
		for i in 0...(POEM.length - 1)
			this_line = 'This is ' + POEM[-2][1]
			that_line = "\nthat " + POEM.last[0] + ' ' + POEM.last[1] + that_line 
			stanza = this_line + that_line
			poem += "\n\n" + stanza
			POEM.pop
		end
		poem + "\n"
	end

end

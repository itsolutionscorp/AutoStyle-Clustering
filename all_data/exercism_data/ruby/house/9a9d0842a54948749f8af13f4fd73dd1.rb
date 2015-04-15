class House

	@@THAT_LINES = [ 
		"belonged to the farmer sowing his corn",
		"kept the rooster that crowed in the morn",
		"woke the priest all shaven and shorn",
		"married the man all tattered and torn",
		"kissed the maiden all forlorn",
		"milked the cow with the crumpled horn",
		"tossed the dog",
		"worried the cat",
		"killed the rat",
		"ate the malt" ]

	@@OPENING_LINES = [
		"house that Jack built.",
		"malt",
		"rat",
		"cat",
		"dog",
		"cow with the crumpled horn",
		"maiden all forlorn",
		"man all tattered and torn",
		"priest all shaven and shorn",
		"rooster that crowed in the morn",
		"farmer sowing his corn",
		"horse and the hound and the horn" ]

	def self.recite
		[].tap do |song|
			(0...@@OPENING_LINES.length).each do |i|
				song << verse(i)
			end
		end.join("\n")
	end

	def self.verse(n)
		sing = "This is the #{@@OPENING_LINES[n]}\n"
		sing << that_part(n-1) << "\n" unless n == 0
		sing 
	end

	def self.that_part(n)
		if n == 0
			return "that lay in the house that Jack built."
		end
		
		"that #{@@THAT_LINES[-n]}\n" << that_part(n-1)
	end

end

class FoodChainSong

	ANIMALS = [	
							["fly", ""],
							["spider", "It wriggled and jiggled and tickled inside her."],
							["bird", "How absurd to swallow a bird!"],
							["cat", "Imagine that, to swallow a cat!"],
							["dog", "What a hog, to swallow a dog!"],
							["goat", "Just opened her throat and swallowed a goat!"],
							["cow", "I don't know how she swallowed a cow!"],	
							["horse", ""]
						]
	
	def verse(number)
		song = "I know an old lady who swallowed a " 
		if number < 8
			for n in number.downto(2) do 
				 song += ANIMALS[n - 1][0] + "#{ n == 2 && number != 2 ? ANIMALS[n - 1][1].gsub(/It/, ' that') : "." + "#{ ("\n" + ANIMALS[n - 1][1]) unless n > 2 && n != number }" }" + "\n" + "She swallowed the " + ANIMALS[n - 1][0] + " to catch the "  
			end	
			song += ANIMALS[0][0]
			song += ".\n" + "I don't know why she swallowed the fly. Perhaps she'll die.\n"
		elsif number == 8
			song += ANIMALS[7][0] +".\n" + "She's dead, of course!\n"
		end
		song
	end
	
	def verses(from, to)
		song = ''
		(from..to).each do |n|
			song += verse(n) + "\n"
		end
		song
	end
	
	def sing
		self.verses(1,8)
	end

end

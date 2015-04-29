class FoodChainSong

	def swallow(animal)
		return "I know an old lady who swallowed a #{animal}.\n"
	end

	def flyface 
		"I don't know why she swallowed the fly. Perhaps she'll die.\n"
	end
	def wriggle
		"wriggled and jiggled and tickled inside her."
	end
	def catch(first,second)
		"She swallowed the #{first} to catch the #{second}"
	end

	def verse(arg)
		@new = ".\n"
		@spifi = catch('spider','fly')
		@bispi = catch('bird','spider')
		@wrigglingbird = @bispi + " that "+wriggle + "\n"
		@catbird = catch('cat','bird') + @new
		
		def two
			@wrigglingbird + @spifi + @new + flyface
		end
		def three
			@catbird + two
		end
		def four
			catch('dog','cat') + @new + three
		end
		def five 
			catch('goat','dog')+ @new +four
		end
		def six 
			catch('cow','goat')+ @new + five
		end
		if arg == 1
			swallow('fly') + flyface 	
		elsif arg == 2
			swallow('spider') + "It "+ wriggle + "\n" + catch('spider','fly') +".\n" +flyface
		elsif arg == 3
			swallow('bird') + "How absurd to swallow a bird!\n" + two
		elsif arg == 4 
			swallow('cat') + "Imagine that, to swallow a cat!\n" + three
		elsif arg == 5
			swallow('dog') + "What a hog, to swallow a dog!\n" + four
		elsif arg == 6
			swallow('goat') + "Just opened her throat and swallowed a goat!\n" + five
		elsif arg == 7 
			swallow('cow') + "I don't know how she swallowed a cow!\n" + six
		elsif arg == 8 
			swallow('horse') + "She's dead, of course!\n"
      	end
	end
	def verses(b,e)
	 a = [] 
	 a << verse(b)  
	 a << verse(e)
	 a.join
	end
end

class FoodChainSong

	@@animal = ["fly","spider","bird","cat","dog","goat","cow","horse"]
	@@desc = ["", "It wriggled and jiggled and tickled inside her.\n", "How absurd to swallow a bird!\n", "Imagine that, to swallow a cat!\n", "What a hog, to swallow a dog!\n", "Just opened her throat and swallowed a goat!\n", "I don't know how she swallowed a cow!\n", "She's dead, of course!\n"]
	
	def verse num
		str = ""
		str << "I know an old lady who swallowed a #{@@animal[num - 1]}.\n"
		str << @@desc[num - 1]

		return str if num == 8

		loop do
			break if num < 2
			str << "She swallowed the #{@@animal[num - 1]} to catch the #{@@animal[num - 2]}"
			str << " that wriggled and jiggled and tickled inside her" if num == 3
			str << ".\n"
			num -= 1
		end

		str + "I don't know why she swallowed the fly. Perhaps she'll die.\n"
	end

	def verses first,last
		str = ""
		(first..last).each{ |i|
			str << verse(i) + "\n"
		}
		str
	end

	def sing
		verses 1,8
	end

end

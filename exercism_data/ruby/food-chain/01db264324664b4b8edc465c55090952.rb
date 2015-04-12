class FoodChainSong
	HASH = {
		"fly" => "I don't know why she swallowed the fly. Perhaps she'll die.", 
		"spider" => "It wriggled and jiggled and tickled inside her.", 
		"bird" => "How absurd to swallow a bird!", 
		"cat" => "Imagine that, to swallow a cat!", 
		"dog" => "What a hog, to swallow a dog!", 
		"goat" => "Just opened her throat and swallowed a goat!", 
		"cow" => "I don't know how she swallowed a cow!", 
		"horse" => "She's dead, of course!"
		}
	
	def initialize
	end
	
	def verse(number)
		i = number - 1
		animal = HASH.keys[i]
		string = "I know an old lady who swallowed a #{animal}.\n"
		string << HASH[animal] + "\n"
		return string if number == 1 || number == 8
		while i >= 1
			predator = HASH.keys[i] 
			prey = HASH.keys[i-1] 
			if i == 2
				string << "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
			else
				string << "She swallowed the #{predator} to catch the #{prey}.\n"
			end
			i = i - 1
		end
		string << HASH["fly"] + "\n"	
	end
	
	def verses(start, finish)
		array =*(start..finish)
		array.map!{ |x| verse(x) }
		string = array.join("\n")
		string << "\n"
	end
	
	def sing 
		verses(1, 8)
	end
end
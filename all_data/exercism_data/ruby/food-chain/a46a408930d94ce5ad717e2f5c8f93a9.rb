class FoodChainSong

	def looping_section(x)
		case x
		when 1 
			"I don't know why she swallowed the fly. Perhaps she'll die.\n"
		when 2
			"She swallowed the spider to catch the fly.\n#{looping_section( x-1 )}"
		when 3
			"She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n#{looping_section( x-1 )}"
		when 4
			"She swallowed the cat to catch the bird.\n#{looping_section( x-1 )}"
		when 5
			"She swallowed the dog to catch the cat.\n#{looping_section( x-1 )}"
		when 6
			"She swallowed the goat to catch the dog.\n#{looping_section( x-1 )}"
		when 7
			"She swallowed the cow to catch the goat.\n#{looping_section( x-1 )}"
		end
	end

	def verse(x)
		x = x.to_i
		y = x.to_i - 2
		old_lady = "I know an old lady who swallowed a "
		@animals = ["spider", "bird", "cat", "dog", "goat", "cow", "horse"]
		@animal_song = {"spider" => "It wriggled and jiggled and tickled inside her.\n", 
							"bird" => "How absurd to swallow a bird!\n", 
							"cat" => "Imagine that, to swallow a cat!\n",
							"dog" => "What a hog, to swallow a dog!\n", 
							"goat" => "Just opened her throat and swallowed a goat!\n",
							"cow" => "I don't know how she swallowed a cow!\n", 
							"horse" => "She's dead, of course!\n"}
		if x == 1
			return old_lady + "fly.\n" + looping_section(x)
  		elsif (2..7).include?(x)
  			return "I know an old lady who swallowed a #{@animals[y]}.\n" + @animal_song[@animals[y]] + looping_section(x)
  		elsif x == 8
  			return old_lady + @animals[6] + ".\n" + @animal_song[@animals[6]]
  		end
  	end

	def verses(x, y)
		verses_range = x..y
		verses_to_sing = ""
		verses_range.each do |number|
			verses_to_sing << verse(number) + "\n"
		end
		return verses_to_sing
	end

	def sing
		return verses(1 ,8)
	end

end

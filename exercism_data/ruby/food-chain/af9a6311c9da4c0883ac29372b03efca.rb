class FoodChainSong
	
	def initialize
		@exclamations = []
		@prehistory = []
		prepare_verse_data
	end

	def prepare_verse_data
		@animals = ["", "fly", "spider", "bird", "cat", "dog", "goat", "cow"]
		
		@prehistory << ""
		@prehistory << "I don't know why she swallowed the fly. Perhaps she'll die.\n"
		@prehistory << "She swallowed the spider to catch the fly.\n"
		@prehistory << "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
		@prehistory << "She swallowed the cat to catch the bird.\n"
		@prehistory << "She swallowed the dog to catch the cat.\n"
    @prehistory << "She swallowed the goat to catch the dog.\n"  
    @prehistory << "She swallowed the cow to catch the goat.\n"

    @exclamations << "" << ""
		@exclamations << "It wriggled and jiggled and tickled inside her.\n"
		@exclamations << "How absurd to swallow a bird!\n"
		@exclamations << "Imagine that, to swallow a cat!\n"
		@exclamations << "What a hog, to swallow a dog!\n"
		@exclamations << "Just opened her throat and swallowed a goat!\n"
		@exclamations << "I don't know how she swallowed a cow!\n"
	end

	def verse number
		if number < 8
			build_verse_at number
		else
			final_verse
		end
	end

	def build_verse_at number
		cumulative_song = "I know an old lady who swallowed a #{@animals[number]}.\n"
		cumulative_song += @exclamations[number]

		(1..number).reverse_each do |i|
			cumulative_song += @prehistory[i]
		end

		cumulative_song
	end

	def final_verse
		"I know an old lady who swallowed a horse.\n" + "She's dead, of course!\n"
	end

	def verses first, last
		verses = ""
		(first..last).each do |number|
			verses += (verse number) + "\n"
		end

		verses
	end

	def sing
		verses(1, 8)
	end
end

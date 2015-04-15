class FoodChainSong
	def initialize
		@food_chain_animals = ["fly","spider","bird","cat","dog","goat","cow","horse"]
		@song = ""
	end
	def sing
		1.upto(8) do |i|
			@song << verse(i) + "\n"
		end
		return @song
	end
	def verses(verse_init,verse_ending)
		for i in verse_init..verse_ending
			@song << verse(i) + "\n"
		end
		return @song
	end
	def verse(versenumber) 
		string_verse = "I know an old lady who swallowed a #{@food_chain_animals[versenumber-1]}.\n"
		current_verse = case versenumber
		when 1 then
			#nothing
		when 2 then
			string_verse += "It wriggled and jiggled and tickled inside her.\n" +
      		"She swallowed the spider to catch the fly.\n"
      	when 3 then
      		string_verse += "How absurd to swallow a bird!\n" +
      		"She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
      		"She swallowed the spider to catch the fly.\n"
      	when 4 then
      		string_verse += "Imagine that, to swallow a cat!\n" +
      		"She swallowed the cat to catch the bird.\n" +
      		"She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
      		"She swallowed the spider to catch the fly.\n"
      	when 5 then
      		string_verse +=   "What a hog, to swallow a dog!\n" +
      		"She swallowed the dog to catch the cat.\n" +
		    "She swallowed the cat to catch the bird.\n" +
      		"She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
      		"She swallowed the spider to catch the fly.\n"
      	when 6 then
      		 string_verse += "Just opened her throat and swallowed a goat!\n" +
      			"She swallowed the goat to catch the dog.\n" +
      			"She swallowed the dog to catch the cat.\n" +
      			"She swallowed the cat to catch the bird.\n" +
      			"She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
      			"She swallowed the spider to catch the fly.\n"
      	when 7 then
      		string_verse += "I don't know how she swallowed a cow!\n" +
		      "She swallowed the cow to catch the goat.\n" +
		      "She swallowed the goat to catch the dog.\n" +
		      "She swallowed the dog to catch the cat.\n" +
		      "She swallowed the cat to catch the bird.\n" +
		      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
		      "She swallowed the spider to catch the fly.\n"
		when 8 then
			string_verse += "She's dead, of course!\n"
		end
		if versenumber != 8 then
			string_verse += "I don't know why she swallowed the fly. Perhaps she'll die.\n" 
		end
		return string_verse
	end
end

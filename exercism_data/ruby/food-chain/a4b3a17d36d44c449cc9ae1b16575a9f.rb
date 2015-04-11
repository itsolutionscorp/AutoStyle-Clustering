class FoodChainSong
	def initialize
		@food_chain_animals = ["fly","spider","bird","cat","dog","goat","cow","horse"]
	end
	def sing
		@song = ""
		1.upto(8) do |i|
			@song << verse(i) + "\n"
		end
		return @song
	end
	def verses(verse_init,verse_ending)
		@song = ""
		for i in verse_init..verse_ending
			@song << verse(i) + "\n"
		end
		return @song
	end
	def swallow_chain(ending)
		chain = ""
		ending.downto(1) do |i|
			chain << "She swallowed the #{@food_chain_animals[i]} to catch the #{@food_chain_animals[i-1]}"
			chain << " that wriggled and jiggled and tickled inside her" if i == 2
			chain << ".\n"
		end
		return chain
	end
	def verse(versenumber) 
		string_verse = "I know an old lady who swallowed a #{@food_chain_animals[versenumber-1]}.\n"
		current_verse = case versenumber
		when 1 then
			#nothing
		when 2 then
			string_verse << "It wriggled and jiggled and tickled inside her.\n" 
      	when 3 then
      		string_verse << "How absurd to swallow a bird!\n" 
      	when 4 then
      		string_verse << "Imagine that, to swallow a cat!\n" 
      	when 5 then
      		string_verse <<   "What a hog, to swallow a dog!\n" 
      	when 6 then
      		 string_verse << "Just opened her throat and swallowed a goat!\n" 
      	when 7 then
      		string_verse << "I don't know how she swallowed a cow!\n" 
		when 8 then
			string_verse << "She's dead, of course!\n"
		end
		if versenumber != 8 then
			string_verse << swallow_chain(versenumber-1) if versenumber != 1
			string_verse << "I don't know why she swallowed the fly. Perhaps she'll die.\n" 
		end
		return string_verse
	end
end

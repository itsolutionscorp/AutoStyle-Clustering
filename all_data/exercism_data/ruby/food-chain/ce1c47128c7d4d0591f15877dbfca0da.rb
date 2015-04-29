class FoodChainSong

	ANIMALS = ["fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"]

	def verses(start_v, end_v)
		(start_v..end_v).map { |v| self.send("verse", v) + "\n" }.join
	end

	def sing
		verses(1, 8)
	end

	def verse(requested_verse)
		case requested_verse
		when 1
			generate_verse_for("fly")
		when 2
			generate_verse_for("spider")
		when 3
			generate_verse_for("bird")
		when 4
			generate_verse_for("cat")
		when 5
			generate_verse_for("dog")
		when 6
			generate_verse_for("goat")
		when 7
			generate_verse_for("cow")
		when 8
			generate_verse_for("horse")
		end
	end

	def effect(animal)
		case animal
		when "spider"
			"It wriggled and jiggled and tickled inside her.\n"
		when "bird"
			"How absurd to swallow a bird!\n"
		when "cat"
			"Imagine that, to swallow a cat!\n"
		when "dog"
			"What a hog, to swallow a dog!\n"
		when "goat"
			"Just opened her throat and swallowed a goat!\n"
		when "cow"
			"I don't know how she swallowed a cow!\n"
		when "horse"
			"She's dead, of course!\n"
		else 
			""
		end	
	end

	def build_verse(animal)
		res = ""
		countdown = ANIMALS.index(animal)
		until countdown < 0
			res += self.send(ANIMALS[countdown])
			countdown -= 1
		end
		res
	end

	def generate_verse_for(animal)
		return "I know an old lady who swallowed a #{animal}.\n" + effect(animal) if animal == ANIMALS.last
		"I know an old lady who swallowed a #{animal}.\n" + effect(animal) + build_verse(animal)
	end

	def cow
		"She swallowed the cow to catch the goat.\n"
	end

	def goat
		"She swallowed the goat to catch the dog.\n"
	end

	def dog
		"She swallowed the dog to catch the cat.\n"
	end

	def cat
		"She swallowed the cat to catch the bird.\n"
	end

	def bird
		"She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" 
	end

	def spider
		"She swallowed the spider to catch the fly.\n"
	end

	def fly
		"I don't know why she swallowed the fly. " \
		"Perhaps she'll die.\n"
	end

end

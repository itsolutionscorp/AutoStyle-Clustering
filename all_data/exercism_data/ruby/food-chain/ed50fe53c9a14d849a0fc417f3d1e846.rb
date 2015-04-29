class FoodChainSong

	@@animalLines = {	
			"fly" => "I don't know why she swallowed the fly. Perhaps she'll die.\n",
			"spider" => "It wriggled and jiggled and tickled inside her.\n",
			"bird" => "How absurd to swallow a bird!\n",
			"cat" => "Imagine that, to swallow a cat!\n",
			"dog" => "What a hog, to swallow a dog!\n",
			"goat" => "Just opened her throat and swallowed a goat!\n",
			"cow" => "I don't know how she swallowed a cow!\n",
			"horse" => "She's dead, of course!\n" }

	@@animals = ["fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"]

	def verse(n)
		animal = @@animals[n-1]
		verseText = ""

		verseText << firstLine(animal)
		verseText << secondLine(animal) unless animal == "fly"
		unless animal == "horse"
			verseText << listAnimals(n-1)
			verseText << lastLine
		end

		verseText
	end

	def verses(a, b)
		(a..b).map { |v| verse(v) << "\n" }.join()
	end

	def sing
		verses(1, @@animals.length)
	end

	private

		def firstLine(animal)
			"I know an old lady who swallowed a " << animal << ".\n"
		end

		def secondLine(animal)
			@@animalLines[animal]
		end

		def lastLine
			secondLine("fly")
		end

		def listAnimals(n)
			lines = ""
			while n > 0
				lines << catchLine(@@animals[n], @@animals[n-1]) << "\n"
				n -= 1
			end

			lines
		end

		def catchLine(a, b)
			if b == "spider"
				"She swallowed the " << a << " to catch the spider that wriggled " +
				"and jiggled and tickled inside her."
			else
				"She swallowed the " << a << " to catch the " << b << "."
			end
		end


end

class FoodChainSong
	attr_reader :start,:eats,:ending
	def initialize
		@start = "I know an old lady who swallowed a "
		@eats = ["","fly","spider","bird","cat","dog","goat","cow","horse"]
		@ending = "I don't know why she swallowed the fly. Perhaps she'll die.\n"
		@seconds = ["How absurd to swallow a bird!\n",
					"Imagine that, to swallow a cat!\n",
					"What a hog, to swallow a dog!\n",
					"Just opened her throat and swallowed a goat!\n",
					"I don't know how she swallowed a cow!\n"
					]
		@spider = "It wriggled and jiggled and tickled inside her.\n"
		@horse =  "She's dead, of course!\n"
	end
	def verse(num)
		verse = ""
		verse = start << eats[num] << ".\n"
		case num
		when 1
			verse << ending
		when 2
			verse << @spider << swallowed(num) << ending
		when 3
			verse << @seconds[num-3] << swallowed(3) << ending
		when 4
			verse << @seconds[num-3] << swallowed(4) << ending
		when 5
			verse << @seconds[num-3] << swallowed(5) << ending
		when 6
			verse << @seconds[num-3] << swallowed(6) << ending
		when 7
			verse << @seconds[num-3] << swallowed(7) << ending
		when 8
			verse << @horse
		end
	end
	def swallowed(num)
		swallow = ""
		until num == 1
			if num == 3
				swallow << "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
				num-=1
			else
				swallow << "She swallowed the #{eats[num]} to catch the #{eats[num-1]}.\n"
				num-=1
			end
		end
		swallow
	end
	def verses(from,to)
		verses = ""
		until from > to
			verses << verse(from) << "\n"
			from+=1
		end
		verses
	end
	def sing
		verses(1,8)
	end
end
song = FoodChainSong.new
puts song.verses(1,2)

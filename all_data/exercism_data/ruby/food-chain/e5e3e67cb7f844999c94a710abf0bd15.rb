class FoodChainSong

	FOOD_CHAIN_GANG = [[:fly, ""], [:spider, "It wriggled and jiggled and tickled inside her.", " that wriggled and jiggled and tickled inside her"], 
						[:bird, "How absurd to swallow a bird!"], [:cat, "Imagine that, to swallow a cat!"],
						[:dog, "What a hog, to swallow a dog!"], [:goat, "Just opened her throat and swallowed a goat!"],
						[:cow, "I don't know how she swallowed a cow!"], [:horse, ""]]
	def initialize
		@verse = ""
	end

	def verse (num)

		verse = ""
		#Always start with A
		verse_part_A(FOOD_CHAIN_GANG[num-1][0])

		if (num > 1 && num < 8)
			#C, D(n-1).times
			verse_part_C(FOOD_CHAIN_GANG[num-1][1])
			$i = num-1
			until $i == 0 do
				if ($i == 2)
					verse_part_D(FOOD_CHAIN_GANG[$i][0], FOOD_CHAIN_GANG[$i-1][0].to_s+FOOD_CHAIN_GANG[$i-1][2])
				else
					verse_part_D(FOOD_CHAIN_GANG[$i][0], FOOD_CHAIN_GANG[$i-1][0])
				end
				$i -= 1
			end	
		end
		#B or #E to end the verse
		if (num != 8)
			verse_part_B
		else
			verse_part_E
		end
		
		return @verse		
	end

	def verses (start_verse, end_verse)
		song = ""
		(start_verse..end_verse).each do |num|
			song += verse(num)+"\n"
		end
		return song
	end

	def sing
		verses(1, 8)
	end

	private

	def verse_part_A(swallowed)
		@verse += "I know an old lady who swallowed a #{swallowed}.\n"
	end

	def verse_part_B
		@verse += "I don't know why she swallowed the fly. Perhaps she'll die.\n"
	end

	def verse_part_C(describe_swallowed)
		@verse += "#{describe_swallowed}\n"
	end

	def verse_part_D(predator, prey)
		@verse += "She swallowed the #{predator} to catch the #{prey}.\n"
	end

	def verse_part_E
		@verse += "She's dead, of course!\n"
	end

	
end

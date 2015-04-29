class FoodChainSong
	def initialize
	end

	$array = ["I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n",
			"I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n" \
  		"She swallowed the spider to catch the fly.\n" \
    	"I don't know why she swallowed the fly. Perhaps she'll die.\n",
    	"I know an old lady who swallowed a bird.\n" \
      "How absurd to swallow a bird!\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. Perhaps she'll die.\n",
    	"I know an old lady who swallowed a cat.\n" \
      "Imagine that, to swallow a cat!\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n",
    	"I know an old lady who swallowed a dog.\n" \
      "What a hog, to swallow a dog!\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n",
    	"I know an old lady who swallowed a goat.\n" \
      "Just opened her throat and swallowed a goat!\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n",
    	"I know an old lady who swallowed a cow.\n" \
      "I don't know how she swallowed a cow!\n" \
      "She swallowed the cow to catch the goat.\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n",
    	"I know an old lady who swallowed a horse.\n" \
      "She's dead, of course!\n"]

      $array_2 = ["I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n\n",
			"I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n" \
  		"She swallowed the spider to catch the fly.\n" \
    	"I don't know why she swallowed the fly. Perhaps she'll die.\n\n",
    	"I know an old lady who swallowed a bird.\n" \
      "How absurd to swallow a bird!\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"]

	def verse v_num_1
		if v_num_1 == 1 then $array[0]
			elsif v_num_1 == 2 then $array[1]
			elsif v_num_1 == 3 then $array[2]
			elsif v_num_1 == 4 then $array[3]
			elsif v_num_1 == 5 then $array[4]
			elsif v_num_1 == 6 then $array[5]
			elsif v_num_1 == 7 then $array[6]
			elsif v_num_1 == 8 then $array[7]
		end
	end

	def verses (ver_1, ver_2)
		if ver_2 == 8 then
			$array_2.join + $array[3..8].join
		else $array_2[ver_1-1..ver_2-1].join
		end
	end

	def sing
		$array_2.join + $array[3...8].join
	end
end

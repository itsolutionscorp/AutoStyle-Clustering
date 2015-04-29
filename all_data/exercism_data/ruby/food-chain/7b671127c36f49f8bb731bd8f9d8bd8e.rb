class FoodChainSong
	def initialize
	end

	def verse v_num_1
		v_1 = "I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n"
		v_2 = "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    v_3 = "I know an old lady who swallowed a bird.\n" \
      "How absurd to swallow a bird!\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    v_4 = "I know an old lady who swallowed a cat.\n" \
      "Imagine that, to swallow a cat!\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
    v_5 = "I know an old lady who swallowed a dog.\n" \
      "What a hog, to swallow a dog!\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
    v_6 = "I know an old lady who swallowed a goat.\n" \
      "Just opened her throat and swallowed a goat!\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
    v_7 = "I know an old lady who swallowed a cow.\n" \
      "I don't know how she swallowed a cow!\n" \
      "She swallowed the cow to catch the goat.\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
    v_8 = "I know an old lady who swallowed a horse.\n" \
      "She's dead, of course!\n"
		if v_num_1 == 1 then v_1
			elsif v_num_1 == 2 then v_2
			elsif v_num_1 == 3 then v_3
			elsif v_num_1 == 4 then v_4
			elsif v_num_1 == 5 then v_5
			elsif v_num_1 == 6 then v_6
			elsif v_num_1 == 7 then v_7
			elsif v_num_1 == 8 then v_8
		end
	end

	def verses (ver_1, ver_2)
		array = ["I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n\n",
			"I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n" \
  		"She swallowed the spider to catch the fly.\n" \
    	"I don't know why she swallowed the fly. Perhaps she'll die.\n\n",
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
		array_2 = array[ver_1-1..ver_2-1].join
	end

	def sing
		v_1 = "I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n\n"
		v_2 = "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. Perhaps she'll die.\n\n"
    v_3 = "I know an old lady who swallowed a bird.\n" \
      "How absurd to swallow a bird!\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    v_4 = "I know an old lady who swallowed a cat.\n" \
      "Imagine that, to swallow a cat!\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
    v_5 = "I know an old lady who swallowed a dog.\n" \
      "What a hog, to swallow a dog!\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
    v_6 = "I know an old lady who swallowed a goat.\n" \
      "Just opened her throat and swallowed a goat!\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
    v_7 = "I know an old lady who swallowed a cow.\n" \
      "I don't know how she swallowed a cow!\n" \
      "She swallowed the cow to catch the goat.\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
    v_8 = "I know an old lady who swallowed a horse.\n" \
      "She's dead, of course!\n"
    v_1 + v_2 + v_3 + v_4 + v_5 + v_6 + v_7 + v_8
	end
end

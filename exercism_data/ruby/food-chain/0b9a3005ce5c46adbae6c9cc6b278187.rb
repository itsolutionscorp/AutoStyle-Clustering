class FoodChainSong
  
  # put all the lyrics on the top and in an array to make it clean and readable

  ANIMALS = ["fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"]
  UNIQUE = ['',
            "It wriggled and jiggled and tickled inside her.\n", 
            "How absurd to swallow a bird!\n", 
            "Imagine that, to swallow a cat!\n", 
            "What a hog, to swallow a dog!\n", 
            "Just opened her throat and swallowed a goat!\n", 
            "I don't know how she swallowed a cow!\n", 
            "She's dead, of course!\n"]
  CATCH = ["She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
           "She swallowed the spider to catch the fly.\n",
           "I don't know why she swallowed the fly. Perhaps she'll die.\n"]

  def verse(num)
    "I know an old lady who swallowed a #{ANIMALS[num-1]}.\n" << unique(num) << loop(num)  	
  end

  def verses(v1, v2, final='')
    v1.upto(v2) {|x| final << verse(x) + "\n"}
    final
  end

  def sing
  	verses(1,8)
  end

  def unique(num)
    UNIQUE[num-1] 
  end

  def loop(num)
	if num > 3 && num < 8
		# used recursion to keep looping as long as num > 3
      "She swallowed the #{ANIMALS[num-1]} to catch the #{ANIMALS[num-2]}.\n" + loop(num-1)
    elsif num == 8
      ''
    elsif num == 1
      CATCH[2]
    elsif num == 2
      CATCH[1..2].join
    else
      CATCH.join
	  end
  end

end

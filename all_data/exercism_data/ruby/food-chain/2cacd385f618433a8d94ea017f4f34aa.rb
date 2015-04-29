class FoodChainSong
  ANIMALS = ['fly', 'spider', 'bird', 'cat', 'dog', 'goat', 'cow', 'horse']
  REMARKS = ["I don't know why she swallowed the fly. Perhaps she'll die.",
             "It wriggled and jiggled and tickled inside her.",
             "How absurd to swallow a bird!",
             "Imagine that, to swallow a cat!",
             "What a hog, to swallow a dog!",
             "Just opened her throat and swallowed a goat!",
             "I don't know how she swallowed a cow!",
             "She's dead, of course!"]
  SPIDER_PHRASE = " that wriggled and jiggled and tickled inside her"

  def verse n
    raise ArgumentError unless n > 0 && n < 9
    verse = "I know an old lady who swallowed a #{ANIMALS[n-1]}.\n"
    verse += "#{REMARKS[n-1]}\n"
    return verse if n == 1 || n == 8
    animal_pairs = ANIMALS[0..n-2].zip(ANIMALS[1..n-1]).reverse
    verse += animal_pairs.inject "" do |s, (animal1, animal2)|
      animal1 += SPIDER_PHRASE if animal1 == 'spider'
      s + "She swallowed the #{animal2} to catch the #{animal1}.\n"
    end
    verse += "#{REMARKS[0]}\n"
  end

  def verses first, last
    raise ArgumentError unless first > 0 && last < 9 && first < last
    "#{(first..last).map { |n| verse n }.join "\n"}\n"
  end

  def sing
    verses(1, 8)
  end
end

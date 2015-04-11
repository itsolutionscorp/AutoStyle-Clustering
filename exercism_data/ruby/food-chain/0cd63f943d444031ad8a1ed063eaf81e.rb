class FoodChainSong
  # pad with nil at front to make it 1-based
  ANIMALS = [ nil, :fly, :spider, :bird, :cat, :dog, :goat, :cow, :horse ]

  # the fly verse doesn't get a tag
  TAGS = {
    fly:    "",
    spider: "It wriggled and jiggled and tickled inside her.\n",
    bird:   "How absurd to swallow a bird!\n",
    cat:    "Imagine that, to swallow a cat!\n",
    dog:    "What a hog, to swallow a dog!\n",
    goat:   "Just opened her throat and swallowed a goat!\n",
    cow:    "I don't know how she swallowed a cow!\n",
    horse:  "She's dead, of course!\n"
  }

  def verse(n)
    # bound check
    raise ArgumentError unless (1..ANIMALS.size).include?(n)

    # lead and tag
    animal = ANIMALS[n]
    verse = "I know an old lady who swallowed a #{animal}.\n"
    verse << TAGS[animal]

    # that's all for the horse verse
    return verse if animal == :horse

    # "She swallowed the..." series
    n.downto(2).each do |i|
      predator = ANIMALS[i]
      prey = ANIMALS[i - 1]
      if predator == :bird
        # the bird one varies with the "wriggle" bit
        verse << "She swallowed the #{predator} to catch the #{prey} that wriggled and jiggled and tickled inside her.\n"
      else
        verse << "She swallowed the #{predator} to catch the #{prey}.\n"
      end
    end

    # finish it off
    verse << "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    return verse
  end

  def verses(first, last)
    (first..last).map{ |i| verse(i) + "\n" }.join("")
  end

  def sing
    verses(1, 8)
  end
end

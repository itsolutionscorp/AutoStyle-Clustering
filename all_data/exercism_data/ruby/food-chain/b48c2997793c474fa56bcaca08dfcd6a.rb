class FoodChainSong
  ANIMALS = %w(fly spider bird cat dog goat cow horse)
  FIRST_LINE = "I know an old lady who swallowed a %{animal}.\n"
  SECOND_LINES = [
    nil,
    "It wriggled and jiggled and tickled inside her.\n",
    "How absurd to swallow a bird!\n",
    "Imagine that, to swallow a cat!\n",
    "What a hog, to swallow a dog!\n",
    "Just opened her throat and swallowed a goat!\n",
    "I don't know how she swallowed a cow!\n",
  ]
  MIDDLE_LINES = [
      "She swallowed the spider to catch the fly.\n",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
      "She swallowed the cat to catch the bird.\n",
      "She swallowed the dog to catch the cat.\n",
      "She swallowed the goat to catch the dog.\n",
      "She swallowed the cow to catch the goat.\n",
  ]
  LAST_LINE = "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  VERY_LAST_LINE = "She's dead, of course!\n"

  def verse(n)
    animal = ANIMALS[n - 1]

    lines = [ FIRST_LINE % { animal: animal } ]

    if n < ANIMALS.count
      lines.concat [
        *SECOND_LINES.slice(n - 1, 1),
        *MIDDLE_LINES.slice(0, n - 1).reverse,
        LAST_LINE
      ]
    else
      lines << VERY_LAST_LINE
    end

    lines.join
  end

  def verses(first = 1, last = ANIMALS.count)
    (first..last).collect { |n| verse(n) + "\n" }.join
  end

  def sing
    verses
  end
end

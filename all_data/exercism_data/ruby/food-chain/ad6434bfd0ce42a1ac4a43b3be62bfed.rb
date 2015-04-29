class FoodChainSong
  ANIMALS = %w[fly spider bird cat dog goat cow horse]
  COMMENTS = [
    "",
    "It wriggled and jiggled and tickled inside her.\n",
    "How absurd to swallow a bird!\n",
    "Imagine that, to swallow a cat!\n",
    "What a hog, to swallow a dog!\n",
    "Just opened her throat and swallowed a goat!\n",
    "I don't know how she swallowed a cow!\n",
    "She's dead, of course!\n"
  ]

  def verse(number)
    verse = ""
    animals = ANIMALS.first(number).reverse

    verse << "I know an old lady who swallowed a #{animals.first}.\n"

    if number < ANIMALS.length
      verse << COMMENTS[number - 1]
      verse << middle_part(animals - [animals.first(2)])

      verse << "I don't know why she swallowed the #{animals.last}. Perhaps she'll die.\n"
    else
      verse << "She's dead, of course!\n"
    end

    verse
  end

  def verses(start, stop)
    (start..stop).map { |i| verse(i) + "\n" }.join
  end

  def sing
    verses(1, ANIMALS.length)
  end

  private

  def middle_part(animals)
    animals.each_cons(2).map { |(animal, old_animal)|
      if old_animal == "spider"
        old_animal += " that wriggled and jiggled and tickled inside her"
      end

      "She swallowed the #{animal} to catch the #{old_animal}.\n"
    }.join
  end
end

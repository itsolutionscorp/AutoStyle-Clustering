class FoodChainSong
  def verse(number)
    case number
    when 1, last_verse_number
      intro(number)
    else
      intro(number) + chorus(number)
    end
  end

  def verses(from, to)
    [*from..to].each_with_object("") do |verse_number, lyrics|
      lyrics << verse(verse_number) + "\n"
    end
  end

  def sing
    verses(1, last_verse_number)
  end

  private

  def chain
    {
      fly: "I don't know why she swallowed the fly. Perhaps she'll die.\n",
      spider: "wriggled and jiggled and tickled inside her",
      bird: "How absurd to swallow a bird!\n",
      cat: "Imagine that, to swallow a cat!\n",
      dog: "What a hog, to swallow a dog!\n",
      goat: "Just opened her throat and swallowed a goat!\n",
      cow: "I don't know how she swallowed a cow!\n",
      horse: "She's dead, of course!\n"
    }
  end

  def last_verse_number
    chain.size
  end

  def animal(verse_number)
    chain.keys[verse_number - 1]
  end

  def swallow_animal(verse_number)
    animal = animal(verse_number)
    animal == :spider ? "#{animal} that #{chain[animal]}" : animal
  end

  def action(verse_number)
    animal = animal(verse_number)
    animal == :spider ? "It #{chain[animal]}.\n" : chain[animal]
  end

  def intro(verse_number)
    "I know an old lady who swallowed a #{animal(verse_number)}.\n#{action(verse_number)}"
  end

  def chorus(verse_number)
    swallow_lines(verse_number) + action(1)
  end

  def swallow_lines(verse_number)
    verse_number.downto(2).each_with_object("") do |number, lines|
      lines << swallow_line(number)
    end
  end

  def swallow_line(verse_number)
    "She swallowed the #{animal(verse_number)} to catch the #{swallow_animal(verse_number - 1)}.\n"
  end
end

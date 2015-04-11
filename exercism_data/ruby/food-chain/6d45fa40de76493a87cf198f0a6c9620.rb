class FoodChainSong
  def verse(n)
    if ANIMALS.length == n
      death_verse 
    else
      regular_verse n
    end
  end

  def verses(first, last)
    lyrics = ''
    first.upto(last) { |i| lyrics << verse(i) << "\n" }
    lyrics
  end

  def sing
    verses(1, ANIMALS.length)
  end

  private
  ANIMALS = %w{fly spider bird cat dog goat cow horse}
  FLAVOR_LINES = ["",
                  "\nIt wriggled and jiggled and tickled inside her.",
                  "\nHow absurd to swallow a bird!",
                  "\nImagine that, to swallow a cat!",
                  "\nWhat a hog, to swallow a dog!",
                  "\nJust opened her throat and swallowed a goat!",
                  "\nI don't know how she swallowed a cow!"]

  def regular_verse(n)
    lyrics = ''
    lyrics << "I know an old lady who swallowed a #{ANIMALS[n - 1]}."
    lyrics << FLAVOR_LINES[n - 1]
    n.downto(2) { |i| lyrics << swallow(i) }
    lyrics << "\nI don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  def death_verse
    "I know an old lady who swallowed a #{ANIMALS.last}." +
    "\nShe's dead, of course!\n"
  end

  def swallow(n)
    if 3 == n
      "\nShe swallowed the #{ANIMALS[n - 1]} to catch the #{ANIMALS[n - 2]}" +
      " that wriggled and jiggled and tickled inside her."
    else
      "\nShe swallowed the #{ANIMALS[n - 1]} to catch the #{ANIMALS[n - 2]}."
    end
  end
end

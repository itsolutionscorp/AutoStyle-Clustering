class FoodChainSong
  FOOD = {
    1 => ["fly", "I don't know why she swallowed the fly. Perhaps she'll die.\n"],
    2 => ["spider", "It wriggled and jiggled and tickled inside her.\n"],
    3 => ["bird", "How absurd to swallow a bird!\n"],
    4 => ["cat", "Imagine that, to swallow a cat!\n"],
    5 => ["dog", "What a hog, to swallow a dog!\n"],
    6 => ["goat", "Just opened her throat and swallowed a goat!\n"],
    7 => ["cow", "I don't know how she swallowed a cow!\n"],
    8 => ["horse", "She's dead, of course!\n"]
  }

  def initialize

  end

  def verse(number)
    verse = "I know an old lady who swallowed a #{ FOOD[number][0] }.\n"

    if number == 8
      # horse's special part
      verse += FOOD[8][1]
      return verse
    end

    if number > 1
      verse += FOOD[number][1]
      number.downto(2) do |i|
        if i != 3
          verse += "She swallowed the #{FOOD[i][0]} to catch the #{FOOD[i - 1][0]}.\n"
        else
          verse += "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
        end
      end
    end

    verse += FOOD[1][1]
  end

  def verses(first, last)
    verses = ""
    (first..last).each do |v|
      verses += verse(v) + "\n"
    end

    verses
  end

  def sing
    verses(1, 8)
  end
end

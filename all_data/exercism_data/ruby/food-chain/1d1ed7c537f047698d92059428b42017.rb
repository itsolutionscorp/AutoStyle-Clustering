class FoodChainSong

  def initialize
    @animals = { 1 => "fly", 2 => "spider", 3 => "bird", 4 => "cat", 5 => "dog",
                 6 => "goat", 7 => "cow", 8 => "horse" }
    @comments = { 1 => "",
                  2 => "It wriggled and jiggled and tickled inside her.\n",
                  3 => "How absurd to swallow a bird!\n",
                  4 => "Imagine that, to swallow a cat!\n",
                  5 => "What a hog, to swallow a dog!\n",
                  6 => "Just opened her throat and swallowed a goat!\n",
                  7 => "I don't know how she swallowed a cow!\n" }
  end

  def verse(number)
    verse = ""
    verse << swallowed(number)
    verse << comment(number)
    verse << reason(number)
    verse << punchline(number)
    verse
  end

  def verses(first, last)
    range = (first..last)
    verses = ""
    range.each do |number|
      verses << verse(number)
      verses << "\n"
    end
    verses
  end

  def sing
    all_verses = (1..8)
    song = ""
    all_verses.each do |number|
      song << verse(number)
      song << "\n"
    end
    song
  end

  def swallowed(number)
    "I know an old lady who swallowed a #{@animals[number]}.\n"
  end

  def comment(number)
    "#{@comments[number]}"
  end

  def reason(number)
    reason = ""
    if (1..7).include?(number)
      until number == 1
        reason << "She swallowed the #{@animals[number]} to catch the #{@animals[number-1]}"
        reason << " that wriggled and jiggled and tickled inside her" if number == 3
        reason << ".\n"
        number -= 1
      end
    end
    reason
  end

  def punchline(number)
    if number == 8
      "She's dead, of course!\n"
    else
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    end
  end
end

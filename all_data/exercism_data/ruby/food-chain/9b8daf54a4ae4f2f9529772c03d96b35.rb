class FoodChainSong
  def initialize
    @verse_bank = [
      ["fly"    , ""],
      ["spider" , "wriggled and jiggled and tickled inside her.\n"],
      ["bird"   , "How absurd to swallow a bird!\n"],
      ["cat"    , "Imagine that, to swallow a cat!\n"],
      ["dog"    , "What a hog, to swallow a dog!\n"],
      ["goat"   , "Just opened her throat and swallowed a goat!\n"],
      ["cow"    , "I don't know how she swallowed a cow!\n"],
      ["horse"  , ""]
    ]
  end
  def verse(num)
    index = num - 1
    return begin_verse(index) + final_line if num == @verse_bank.count
    begin_verse(index) + mid_verse(index) + end_verse
  end

  def verses(from_verse, to_verse)
    song = ""
    (from_verse..to_verse).each { |verse_num| song += verse(verse_num) + "\n" }
    song
  end

  def sing
    verses(1, @verse_bank.count)
  end

  private

    def begin_verse(verse_index)
      animal = @verse_bank[verse_index][0]
      #special case for verse 2
      statement = (verse_index == 1 ? "It " : "")
      statement += @verse_bank[verse_index][1]
      "I know an old lady who swallowed a #{animal}.\n#{statement}"
    end

    def mid_verse(verse_index)
      return "" if verse_index == 0
      animal = @verse_bank[verse_index][0]
      other_animal = @verse_bank[verse_index-1][0]
      #Special case for index 2
      other_animal += (verse_index == 2 ? " that #{@verse_bank[1][1]}" : ".\n")
      #Recursively build mid verse lines
      "She swallowed the #{animal} to catch the #{other_animal}" +
       mid_verse(verse_index-1)

    end

    def end_verse
      first_animal = @verse_bank.first[0]
      "I don't know why she swallowed the #{first_animal}. Perhaps she'll die.\n"
    end

    def final_line
      "She's dead, of course!\n"
    end
end

class FoodChainSong
  
  START_LINES = {
    1 => "I know an old lady who swallowed a fly.\n",
    2 => "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n",
    3 => "I know an old lady who swallowed a bird.\nHow absurd to swallow a bird!\n",
    4 => "I know an old lady who swallowed a cat.\nImagine that, to swallow a cat!\n",
    5 => "I know an old lady who swallowed a dog.\nWhat a hog, to swallow a dog!\n",
    6 => "I know an old lady who swallowed a goat.\nJust opened her throat and swallowed a goat!\n",
    7 => "I know an old lady who swallowed a cow.\nI don't know how she swallowed a cow!\n",
    8 => "I know an old lady who swallowed a horse.\nShe's dead, of course!\n",
  }

  MIDDLE_LINES = {
    1 => "She swallowed the spider to catch the fly.\n",
    2 => "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",    
    3 => "She swallowed the cat to catch the bird.\n",
    4 => "She swallowed the dog to catch the cat.\n",
    5 => "She swallowed the goat to catch the dog.\n",
    6 => "She swallowed the cow to catch the goat.\n",
  }

  END_LINES = {
    1 => "I don't know why she swallowed the fly. Perhaps she'll die.\n",
  }

  def verse(verse_num)
    res = ""
    res << START_LINES[verse_num]
    if verse_num < 8
      MIDDLE_LINES.select { |k, v| k < verse_num }.reverse_each do |pair|
        res << pair[1]
      end
      res << END_LINES[1]
    end
    res
  end

  def verses(first_verse, last_verse)
    res = ""
    verse_range = (first_verse..last_verse)
    verse_range.each do |verse_num|
      res << verse(verse_num)
      res << "\n"
    end
    res
  end
  def sing
    verses(1,8)
  end
end

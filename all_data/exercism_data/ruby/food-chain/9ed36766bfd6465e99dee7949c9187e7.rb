class FoodChainSong

# FIRST = "I know an old lady who swallowed a #{animal}"
# LAST = "I don't know why she swallowed the fly. Perhaps she'll die."
VERSES = [
  {}, # there is no verse 0
  {:swallowed => :fly,    :exclamation => ''},
  {:swallowed => :spider, :exclamation => "It wriggled and jiggled and tickled inside her.\n", :description => " that wriggled and jiggled and tickled inside her"},
  {:swallowed => :bird,   :exclamation => "How absurd to swallow a bird!\n" },
  {:swallowed => :cat,    :exclamation => "Imagine that, to swallow a cat!\n" },
  {:swallowed => :dog,    :exclamation => "What a hog, to swallow a dog!\n" },
  {:swallowed => :goat,   :exclamation => "Just opened her throat and swallowed a goat!\n" },
  {:swallowed => :cow,    :exclamation => "I don't know how she swallowed a cow!\n" },
]

# She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.

  def verse(which)
    return handle_first_verse if which == 1
    return handle_last_verse if which == 8
    output = "I know an old lady who swallowed a #{VERSES[which][:swallowed]}.\n"
    output << VERSES[which][:exclamation].to_s
    which.downto(2) do |current_line|
      output << "She swallowed the #{VERSES[current_line][:swallowed]} to catch the #{VERSES[current_line-1][:swallowed]}#{VERSES[current_line-1][:description]}.\n"
    end
    output << "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    output
  end

  def verses(start_verse, end_verse)
    ''.tap do |result|
      start_verse.upto(end_verse) { |which_verse| result << verse(which_verse) + "\n" }
    end
  end

  def sing
    verses(1,8)
  end

  private

  def handle_first_verse
    "I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n"    
  end

  def handle_last_verse
    "I know an old lady who swallowed a horse.\nShe's dead, of course!\n"
  end
end

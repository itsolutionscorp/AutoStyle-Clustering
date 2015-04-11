class FoodChainSong

  TEMPLATE = [
    ['fly', ""],
    ['spider', "It wriggled and jiggled and tickled inside her.\n"],
    ['bird', "How absurd to swallow a bird!\n"],
    ['cat', "Imagine that, to swallow a cat!\n"],
    ['dog', "What a hog, to swallow a dog!\n"],
    ['goat', "Just opened her throat and swallowed a goat!\n"],
    ['cow', "I don't know how she swallowed a cow!\n"],
    ['horse', "She's dead, of course!\n"]
  ]

  def sing
    verses(1, 8)
  end

  def verses(start, finish)
    (start..finish).map{|i| verse(i)}.join("\n") << "\n"
  end

  def verse(i)
    lyrics = "I know an old lady who swallowed a #{TEMPLATE[i - 1][0]}.\n"
    lyrics << TEMPLATE[i-1][1]
    return lyrics if i >= 8
    (2..i).to_a.reverse.each{|i| lyrics << midverse_segment(i) }
    lyrics << "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  private
  def midverse_segment(i)
    segment = "She swallowed the #{TEMPLATE[i-1][0]} "
    segment << "to catch the #{TEMPLATE[i-2][0]}"
    segment << " that wriggled and jiggled and tickled inside her" if i == 3
    segment << ".\n"
  end

end

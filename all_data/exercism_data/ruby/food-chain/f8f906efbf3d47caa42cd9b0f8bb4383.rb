class FoodChainSong

  def initialize
    @verse_lines = [
      "I know an old lady who swallowed a fly.\n",
      "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n",
      "I know an old lady who swallowed a bird.\nHow absurd to swallow a bird!\n",
      "I know an old lady who swallowed a cat.\nImagine that, to swallow a cat!\n",
      "I know an old lady who swallowed a dog.\nWhat a hog, to swallow a dog!\n",
      "I know an old lady who swallowed a goat.\nJust opened her throat and swallowed a goat!\n",
      "I know an old lady who swallowed a cow.\nI don't know how she swallowed a cow!\n",
      "I know an old lady who swallowed a horse.\nShe's dead, of course!\n"
    ]

    @com_lines = [
      "I don't know why she swallowed the fly. Perhaps she'll die.\n",
      "She swallowed the spider to catch the fly.\n",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
      "She swallowed the cat to catch the bird.\n",
      "She swallowed the dog to catch the cat.\n",
      "She swallowed the goat to catch the dog.\n",
      "She swallowed the cow to catch the goat.\n"
    ]
  end

  def verse(max)
    res = []
    0.upto(max - 1).inject(res) { |res, cur| res.push @com_lines[cur] } if max <= @com_lines.length
    res.push @verse_lines[ max - 1 ]
    res.reverse.join('')
  end

  def verses(start_verse, to_verse)
    res = ''
    (start_verse..to_verse).inject(res) { | res, cur | res << "#{verse(cur)}\n" }
  end

  def sing
    verses(1,8)
  end
end

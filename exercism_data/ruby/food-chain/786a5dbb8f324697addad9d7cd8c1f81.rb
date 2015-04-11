class FoodChainSong

  ANIMALS = %w[fly spider bird cat dog goat cow]
  TAGS = ["", "It wriggled and jiggled and tickled inside her.\n",
    "How absurd to swallow a bird!\n", "Imagine that, to swallow a cat!\n",
    "What a hog, to swallow a dog!\n", "Just opened her throat and swallowed a goat!\n",
    "I don't know how she swallowed a cow!\n"
  ]

  def verse(num)
    return "I know an old lady who swallowed a horse.\nShe's dead, of course!\n" if num == 8
    "I know an old lady who swallowed a " + ANIMALS[num-1] + ".\n" + TAGS[num-1] \
    + chain(num-1, num-2) + "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  def verses(start, finish)
    result = ''
    (start..finish).to_a.each { |num| result << verse(num) + "\n" }
    result
  end

  def sing
    verses(1,8)
  end

  def chain(curr, prev)
    return "" if prev < 0
    result = "She swallowed the #{ANIMALS[curr]} to catch the #{ANIMALS[prev]}.\n"
    result[-2..-1] = " that wriggled and jiggled and tickled inside her.\n" if ANIMALS[prev] == "spider"
    result << chain(curr-1, prev-1)
  end
end

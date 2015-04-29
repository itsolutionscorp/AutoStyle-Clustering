class FoodChainSong
  SWALLOWED = "I know an old lady who swallowed a "
  HEAD = [
    nil,
    "fly.",
    "spider.\nIt wriggled and jiggled and tickled inside her.",
    "bird.\nHow absurd to swallow a bird!",
    "cat.\nImagine that, to swallow a cat!",
    "dog.\nWhat a hog, to swallow a dog!",
    "goat.\nJust opened her throat and swallowed a goat!",
    "cow.\nI don't know how she swallowed a cow!",
    "horse."
  ]
  TAIL = [
    nil,
    "I don't know why she swallowed the fly. Perhaps she'll die.",
    "She swallowed the spider to catch the fly.",
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
    "She swallowed the cat to catch the bird.",
    "She swallowed the dog to catch the cat.",
    "She swallowed the goat to catch the dog.",
    "She swallowed the cow to catch the goat.",
    "She's dead, of course!\n",
  ]

  def verse(n)
    "#{SWALLOWED}#{HEAD[n]}\n#{n < 8 ? TAIL[0..n].reverse.join("\n") : TAIL[n]}"
  end

  def verses(start, finish)
    (start..finish).map { |i| verse(i) }.join("\n") + "\n"
  end

  def sing
    verses(1, 8)
  end
end

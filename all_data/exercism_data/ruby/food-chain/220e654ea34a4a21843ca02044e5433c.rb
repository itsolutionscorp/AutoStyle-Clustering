class FoodChainSong
  def verse(number)
    verse = case number
    when 1
      ["I know an old lady who swallowed a fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die."]
    when 2
      ["I know an old lady who swallowed a spider.",
      "It wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die."]
    when 3
      ["I know an old lady who swallowed a bird.",
      "How absurd to swallow a bird!",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die."]
    when 4
      ["I know an old lady who swallowed a cat.",
      "Imagine that, to swallow a cat!",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die."]
    when 5
      ["I know an old lady who swallowed a dog.",
      "What a hog, to swallow a dog!",
      "She swallowed the dog to catch the cat.",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die."]
    when 6
      ["I know an old lady who swallowed a goat.",
      "Just opened her throat and swallowed a goat!",
      "She swallowed the goat to catch the dog.",
      "She swallowed the dog to catch the cat.",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die."]
    when 7
      ["I know an old lady who swallowed a cow.",
      "I don't know how she swallowed a cow!",
      "She swallowed the cow to catch the goat.",
      "She swallowed the goat to catch the dog.",
      "She swallowed the dog to catch the cat.",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die."]
    when 8
      ["I know an old lady who swallowed a horse.",
      "She's dead, of course!"]
    end

    join_lines verse
  end

  def sing
    verses 1, 8
  end

  def verses(first, last)
    verses = []

    (first..last).each do |number|
      verses << verse(number)
    end

    join_lines verses
  end

  private

  def join_lines(lines)
    lines.join("\n") + "\n"
  end
end

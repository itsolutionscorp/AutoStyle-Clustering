class FoodChainSong
  def verse(num)
    if num == 1
      verse1
    elsif num == 2
      verse2
    elsif num == 3
      verse3
    elsif num == 4
      verse4
    elsif num == 5
      verse5
    elsif num == 6
      verse6
    elsif num == 7
      verse7
    elsif num == 8
      verse8
    end
  end

  def verses(first, last)
    first.upto(last).map { |i| verse(i) }.join("\n") + "\n"
  end

  def sing
    verses(1,8)
  end

  private

  def verse1
    "I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  def verse2
    "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n" \
    "She swallowed the spider to catch the fly.\n" \
    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  def verse3
    "I know an old lady who swallowed a bird.\n" \
    "How absurd to swallow a bird!\n" \
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
    "She swallowed the spider to catch the fly.\n" \
    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  def verse4
    "I know an old lady who swallowed a cat.\n" \
    "Imagine that, to swallow a cat!\n" \
    "She swallowed the cat to catch the bird.\n" \
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
    "She swallowed the spider to catch the fly.\n" \
    "I don't know why she swallowed the fly. " \
    "Perhaps she'll die.\n"
    end

  def verse5
    "I know an old lady who swallowed a dog.\n" \
    "What a hog, to swallow a dog!\n" \
    "She swallowed the dog to catch the cat.\n" \
    "She swallowed the cat to catch the bird.\n" \
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
    "She swallowed the spider to catch the fly.\n" \
    "I don't know why she swallowed the fly. " \
    "Perhaps she'll die.\n"
  end

  def verse6
    "I know an old lady who swallowed a goat.\n" \
    "Just opened her throat and swallowed a goat!\n" \
    "She swallowed the goat to catch the dog.\n" \
    "She swallowed the dog to catch the cat.\n" \
    "She swallowed the cat to catch the bird.\n" \
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
    "She swallowed the spider to catch the fly.\n" \
    "I don't know why she swallowed the fly. " \
    "Perhaps she'll die.\n"
  end

  def verse7
    "I know an old lady who swallowed a cow.\n" \
    "I don't know how she swallowed a cow!\n" \
    "She swallowed the cow to catch the goat.\n" \
    "She swallowed the goat to catch the dog.\n" \
    "She swallowed the dog to catch the cat.\n" \
    "She swallowed the cat to catch the bird.\n" \
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
    "She swallowed the spider to catch the fly.\n" \
    "I don't know why she swallowed the fly. " \
    "Perhaps she'll die.\n"
  end

  def verse8
    "I know an old lady who swallowed a horse.\n" \
    "She's dead, of course!\n"
  end
end

FoodChainSong.new.verses(1,8)

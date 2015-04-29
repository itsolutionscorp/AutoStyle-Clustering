class FoodChainSong
  EATABLES = ["fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"]

  def verse(n)
    first_bit(n) << second_bit(n) << last_bit(n)
  end

  def verses(*args)
    args.reduce('') { |memo, n| memo << verse(n) << "\n" }
  end

  def sing
    verses(1, 8)
  end

  def first_bit(n)
    "I know an old lady who swallowed a #{EATABLES[n-1]}.\n"
  end

  def second_bit(n)
    case n
    when 2
      "It wriggled and jiggled and tickled inside her.\n"
    when 3
      "How absurd to swallow a bird!\n"
    when 4
      "Imagine that, to swallow a cat!\n"
    when 5
      "What a hog, to swallow a dog!\n"
    when 6
      "Just opened her throat and swallowed a goat!\n"
    when 7
      "I don't know how she swallowed a cow!\n"
    else
      ""
    end
  end

  def last_bit(n)
    case n
    when 1
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    when 3
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
        last_bit(n-1)
    when 8
      "She's dead, of course!\n"
    else
      "She swallowed the #{EATABLES[n-1]} to catch the #{EATABLES[n-2]}.\n" +
        last_bit(n-1)
    end
  end
end

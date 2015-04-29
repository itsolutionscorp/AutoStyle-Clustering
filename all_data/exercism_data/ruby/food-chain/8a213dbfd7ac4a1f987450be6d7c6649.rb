class FoodChainSong
  CREATURES = %i(fly spider bird cat dog goat cow horse)

  def verse(n)
    creature = CREATURES[n - 1]
    lines = [
      "I know an old lady who swallowed a #{creature}.",
      second_line(creature),
      middle_lines(creature),
      last_line(creature)
    ]
    join(lines)
  end

  def verses(first, last)
    verses = (first..last).flat_map { |n| verse(n) }
    join(verses)
  end

  def sing
    verses(1, 8)
  end

  private

  def second_line(creature)
    case creature
    when :spider
      "It #{wriggled_etc}"
    when :bird
      "How absurd to swallow a bird!"
    when :cat
      "Imagine that, to swallow a cat!"
    when :dog
      "What a hog, to swallow a dog!"
    when :goat
      "Just opened her throat and swallowed a goat!"
    when :cow
      "I don't know how she swallowed a cow!"
    end
  end

  def middle_lines(current_creature)
    return [] if current_creature == :horse

    creatures_downto(current_creature).map { |creature| middle_line(creature) }
  end

  def middle_line(creature)
    case creature
    when :fly, :horse
      nil
    when :bird
      "#{she_swallowed(creature)} that #{wriggled_etc}"
    else
      she_swallowed(creature) << "."
    end
  end

  def she_swallowed(creature)
    "She swallowed the #{creature} to catch the #{previous_creature(creature)}"
  end

  def wriggled_etc
    "wriggled and jiggled and tickled inside her."
  end

  def last_line(creature)
    return "She's dead, of course!" if creature == :horse

    "I don't know why she swallowed the fly. Perhaps she'll die."
  end

  def creatures_downto(creature)
    index = CREATURES.index(creature)
    CREATURES[0..index].reverse
  end

  def previous_creature(creature)
    index = CREATURES.index(creature)
    CREATURES[index - 1]
  end

  def join(lines)
    lines.flatten.compact.join("\n").concat("\n")
  end
end

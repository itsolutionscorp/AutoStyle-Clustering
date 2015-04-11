class VersePart
  attr_reader :animal, :description, :extra

  def initialize(animal, description, extra = "")
    @animal = animal
    @description = description
    @extra = extra
  end

  def sing
    "I know an old lady who swallowed a #{@animal}.\n#{@description}\n"
  end
end

class FoodChainSong

  SPIDER_CHANT = "wriggled and jiggled and tickled inside her"
  FLY_CHANT = "I don't know why she swallowed the fly. " +
            "Perhaps she'll die"

  PART_TEXT = [
    ["fly", FLY_CHANT + ".", ".\n#{FLY_CHANT}"],
    ["spider", "It #{SPIDER_CHANT}.", " that #{SPIDER_CHANT}"],
    ["bird", "How absurd to swallow a bird!"],
    ["cat", "Imagine that, to swallow a cat!"],
    ["dog", "What a hog, to swallow a dog!"],
    ["goat", "Just opened her throat and swallowed a goat!"],
    ["cow", "I don't know how she swallowed a cow!"],
    ["horse", "She's dead, of course!"]
  ]

  VERSE_PARTS = PART_TEXT.map do |animal, desc, extra|
    VersePart.new(animal, desc, extra)
  end

  def sing
    verses 1
  end

  def verses(start, last = VERSE_PARTS.length)
    (start..last).map{ |n| verse n }.join("\n") << "\n"
  end

  def verse(n_plus_1)
    n = n_plus_1 - 1
    VERSE_PARTS[n].sing << (count_down(n) || "")
  end

  private
  def count_down(n)
    n.downto(1).map{ |i| count_down_part(i) }.reduce(:+) unless last?(n)
  end

  def count_down_part(n)
    animal = VERSE_PARTS[n].animal
    next_part = VERSE_PARTS[n - 1]

    "She swallowed the #{animal} to catch the " <<
    "#{next_part.animal}#{next_part.extra}.\n"
  end

  def last?(n)
    n >= VERSE_PARTS.length - 1
  end
end

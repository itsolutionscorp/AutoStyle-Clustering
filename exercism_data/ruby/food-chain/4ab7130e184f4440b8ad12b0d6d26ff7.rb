require 'yaml'

class FoodChainSong
  LINES = [
    ["fly", "I don't know why she swallowed the fly. Perhaps she'll die."],
    ["spider", "It wriggled and jiggled and tickled inside her."],
    ["bird", "How absurd to swallow a bird!"],
    ["cat", "Imagine that, to swallow a cat!"],
    ["dog", "What a hog, to swallow a dog!"],
    ["goat", "Just opened her throat and swallowed a goat!"],
    ["cow", "I don't know how she swallowed a cow!"],
    ["horse", "She's dead, of course!"],
  ]
  def verse(num)
    i = num-1
    lines = []
    lines << opening_line(i)
    lines << second_line(i)
    lines << backstory_lines(i)
    lines << last_line(i)

    lines.compact.join("\n") << "\n"
  end

  def verses(first, last)
    (first..last).map { |num| verse(num) }.join("\n") << "\n"
  end

  def sing
    verses(1,8)
  end

  private

  def opening_line(verse)
    "I know an old lady who swallowed a #{current_animal(verse)}."
  end

  def second_line(i)
    return nil if i < 1
    LINES[i].last
  end

  def backstory_lines(i)
    return nil if i < 1 || i+1 == LINES.length
    LINES[0..i+1].reverse[1..-1].each_cons(2).map do |prev_animal, next_animal|
      "She swallowed the #{prev_animal[0]} to catch the #{caught_animal(next_animal)}."
    end
  end

  def caught_animal(animal)
    if animal[0] == "spider"
      "spider that wriggled and jiggled and tickled inside her"
    else
      animal[0]
    end
  end

  def last_line(i)
    return nil if i+1 == LINES.length
    LINES[0].last
  end

  def current_animal(verse)
    LINES[verse].first
  end
end

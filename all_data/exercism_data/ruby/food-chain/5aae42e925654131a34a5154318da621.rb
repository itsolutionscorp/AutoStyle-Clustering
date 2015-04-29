require 'pry'
class FoodChainSong
  SONG = [ ["fly", "I don't know why she swallowed the fly. Perhaps she'll die."],
           ["spider", "It wriggled and jiggled and tickled inside her."],
           ["bird", "How absurd to swallow a bird!"],
           ["cat", "Imagine that, to swallow a cat!"],
           ["dog", "What a hog, to swallow a dog!"],
           ["goat", "Just opened her throat and swallowed a goat!"],
           ["cow", "I don't know how she swallowed a cow!"],
           ["horse", "She's dead, of course!"] ]

  def verse number
    if number == 1 || number == 8
      initial_phrase(SONG[number - 1])
    else
      initial = initial_phrase(SONG[number - 1])
      last = [*1...number].reverse.map { |n| last_phrase(n) }.inject(:+)
      initial + last
    end
  end

  def verses first, second
    (first..second).map { |number| verse(number) + "\n" }.inject(:+)
  end

  def sing
    verses(1, 8)
  end

  private

  def initial_phrase animal
    "I know an old lady who swallowed a #{animal.first}." + "\n" + "#{animal.last}" + "\n"
  end

  def last_phrase number
    "She swallowed the #{SONG[number].first} to catch the " + animal_say(number-1)
  end

  def animal_say number
    case SONG[number].first
    when "fly"
      "#{SONG[number].first}.\n#{SONG[number].last}\n"
    when "spider"
      spider = SONG[number].last
      "#{SONG[number].first} that #{spider[3..47]}\n"
    else
      "#{SONG[number].first}.\n"
    end
  end
end

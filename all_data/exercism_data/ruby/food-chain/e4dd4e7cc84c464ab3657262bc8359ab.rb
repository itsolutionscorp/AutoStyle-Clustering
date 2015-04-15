require 'pry'

class FoodChainSong
  def verse(number)
    Verse.new(number).assemble
  end

  def verses(first, last)
    (first..last).each_with_object("") { |n, lyrics| lyrics << verse(n) + "\n" }
  end

  def sing
    verses(1, 8)
  end
end

class Verse
  ANIMALS = ["fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"]

  attr_reader :verse_number, :animal, :intro
  def initialize(number)
    @verse_number = number
    @animal = ANIMALS[(verse_number - 1)]
    @intro = "I know an old lady who swallowed a #{animal}.\n"
  end

  def assemble
    intro + exclamation + reason + close
  end

private

  def exclamation
    {
      1 => "",
      2 => "It wriggled and jiggled and tickled inside her.\n",
      3 => "How absurd to swallow a #{animal}!\n",
      4 => "Imagine that, to swallow a #{animal}!\n",
      5 => "What a hog, to swallow a #{animal}!\n",
      6 => "Just opened her throat and swallowed a #{animal}!\n",
      7 => "I don't know how she swallowed a #{animal}!\n",
      8 => ""
    }[verse_number]
  end

  def reason
    reason = ""
    if verse_number > 2 && verse_number < 8
      (verse_number - 2).times do |n|
        reason.prepend "She swallowed the #{ANIMALS[n + 2]} to catch the #{ANIMALS[n + 1]}.\n"
      end
      reason.chomp!(".\n")
      reason << " that wriggled and jiggled and tickled inside her.\n"
    end
    reason
  end

  def close
    close = ""
    close << "She swallowed the spider to catch the fly.\n" if verse_number > 1 && verse_number < 8
    close << "I don't know why she swallowed the fly. Perhaps she'll die." if verse_number < 8
    close << "She's dead, of course!" if verse_number == 8
    close + "\n"
  end
end

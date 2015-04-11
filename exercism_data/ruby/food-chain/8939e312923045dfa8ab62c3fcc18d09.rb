require 'pry'

class FoodChainSong
  def verse(verse_number)
    verse_number = VerseNumber.new(verse_number)

    "#{verse_number.begin_verse}"   +
    "#{verse_number.mid_verse}"     + 
    "#{verse_number.reasoning}"     +
    "#{verse_number.end_verse}"
  end

  def verses(lower, upper)
    lower.upto(upper).map do |n| 
      v = verse(n)
      if n == upper
        v << "\n"
      end
      v
    end.join("\n")
  end

  def sing
    verses(1, 8)
  end
end

class VerseNumber
  attr_reader :number, :swallowables

  def initialize(number)
    @number = number.to_s
    @swallowables = Swallowables.new
  end

  def begin_verse
    "I know an old lady who swallowed a #{current_swallowable_name}.\n"
  end

  def mid_verse
    swallowable_action_for(number)
  end

  def reasoning
    return "" if iterations == 1
    return "" if iterations == 8

    reasons = ""

    iterations.downto(2) do |i|
      reasons << "She swallowed the #{swallowable_name_for(i)} to catch the "
      reasons << "#{swallowable_name_for(i - 1)}"
      if (i - 1) == 2
        reasons << " that wriggled and jiggled and tickled inside her"
      end
      reasons << ".\n"
    end

    reasons
  end

  def end_verse
    return "She's dead, of course!\n" if iterations == 8

    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  private

  def current_swallowable_name
    swallowables.list[number]["name"]
  end

  def swallowable_name_for(number)
    swallowables.list[number.to_s]["name"]
  end

  def swallowable_action_for(number)
    swallowables.list[number.to_s]["action"] 
  end

  def iterations
    number.to_i 
  end

  def previous_number
    (number.to_i - 1).to_s
  end

  def punctuation
    if number.to_i > 2
      "!"
    else
      "."
    end
  end
end

class Swallowables
  def list
    {
      "1" => {
        "name" => "fly",
        "action" => ""
      },
      "2" => {
        "name" => "spider",
        "action" => "It wriggled and jiggled and tickled inside her.\n"
      },
      "3" => {
        "name" => "bird",
        "action" => "How absurd to swallow a bird!\n"
      },
      "4" => {
        "name" => "cat",
        "action" => "Imagine that, to swallow a cat!\n"
      },
      "5" => {
        "name" => "dog",
        "action" => "What a hog, to swallow a dog!\n"
      },
      "6" => {
        "name" => "goat",
        "action" => "Just opened her throat and swallowed a goat!\n"
      },
      "7" => {
        "name" => "cow",
        "action" => "I don't know how she swallowed a cow!\n"
      },
      "8" => {
        "name" => "horse",
        "action" => ""
      }
    }
  end
end

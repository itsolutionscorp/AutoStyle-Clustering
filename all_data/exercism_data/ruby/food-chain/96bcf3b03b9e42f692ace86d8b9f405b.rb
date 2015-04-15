class FoodChainSong
  SECOND_LINE = {
    1 => '',
    2 => "It wriggled and jiggled and tickled inside her.\n",
    3 => "How absurd to swallow a bird!\n",
    4 => "Imagine that, to swallow a cat!\n",
    5 => "What a hog, to swallow a dog!\n",
    6 => "Just opened her throat and swallowed a goat!\n",
    7 => "I don't know how she swallowed a cow!\n",
    8 => ''
  }

  LAST_LINE = Hash.new("I don't know why she swallowed the fly. Perhaps she'll die.\n")
  LAST_LINE[8] = "She's dead, of course!\n"

  CREATURES = {
    1 => ['fly'],
    2 => ['spider', 'spider that wriggled and jiggled and tickled inside her'],
    3 => ['bird'],
    4 => ['cat'],
    5 => ['dog'],
    6 => ['goat'],
    7 => ['cow'],
    8 => ['horse']
  }


  def verse(number)
    first_line(number) +
    second_line(number) +
    middle_lines(number) +
    last_line(number)
  end

  def verses(first, last)
    (first..last).map do |number|
      verse(number) + "\n"
    end.join
  end

  def sing
    (1..8).map do |number|
      verse(number) + "\n"
    end.join
  end

  private

  def first_line(number)
    "I know an old lady who swallowed a #{CREATURES[number].first}.\n"
  end

  def second_line(number)
    SECOND_LINE[number]
  end

  def middle_lines(number)
    return '' if number == 8
    number.downto(2).map do |i|
      "She swallowed the #{CREATURES[i].first} to catch the #{CREATURES[i - 1].last}.\n"
    end.join
  end

  def last_line(number)
    LAST_LINE[number]
  end
end

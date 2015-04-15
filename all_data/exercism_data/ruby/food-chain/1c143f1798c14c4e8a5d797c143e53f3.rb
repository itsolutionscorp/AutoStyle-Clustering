class FoodChainSong
  FOODS = {
    1 => { name: "fly" },
    2 => { name: "spider", surprise: "It wriggled and jiggled and tickled inside her.\n" },
    3 => { name: "bird", surprise: "How absurd to swallow a bird!\n", extra: " that wriggled and jiggled and tickled inside her" },
    4 => { name: "cat", surprise: "Imagine that, to swallow a !\n" },
    5 => { name: "dog", surprise: "What a hog, to swallow a dog!\n" },
    6 => { name: "goat", surprise: "Just opened her throat and swallowed a goat!\n" },
    7 => { name: "cow", surprise: "I don't know how she swallowed a cow!\n" },
    8 => { name: "horse", dead: true }
  }

  def verse(num)
    lyric = beginning_sentence(num)
    lyric << middle_sentence(num)
    lyric << last_sentence(num)
  end

  def verses(start_num, end_num)
    (start_num..end_num).map { |n| verse(n) }.join("\n") + "\n"
  end

  def sing
    verses(1, 8)
  end

  private

  def beginning_sentence(num)
    "I know an old lady who swallowed a #{FOODS[num][:name]}.\n#{FOODS[num][:surprise]}"
  end

  def middle_sentence(num)
    return "" if num < 1 || FOODS[num][:dead]
    (2..num).to_a.reverse.map do |n|
      "She swallowed the #{FOODS[n][:name]} to catch the #{FOODS[n - 1][:name]}#{FOODS[n][:extra]}.\n"
    end.join("")
  end

  def last_sentence(num)
     FOODS[num][:dead] ? "She's dead, of course!\n" : "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end
end

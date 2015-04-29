class BeerSong
  attr_reader :max

  def initialize(max=99)
    @max = max
  end

  def verse(num)
    [first_line(num), second_line(num)].join("\n") << "\n"
  end

  def verses(first, last)
    first.downto(last).map { |v| verse(v) }.join("\n") << "\n"
  end

  def sing
    verses(max, 0)
  end

  private

  def first_line(num)
    "#{num_of_bottles(num).capitalize} of beer on the wall, #{num_of_bottles(num)} of beer."
  end

  def second_line(num)
    if num > 0
      "Take #{num == 1 ? "it" : "one"} down and pass it around, #{num_of_bottles(num-1)} of beer on the wall."
    else
      "Go to the store and buy some more, #{max} bottles of beer on the wall."
    end
  end

  def num_of_bottles(num)
    case num
    when 0
      "no more bottles"
    when 1
      "1 bottle"
    else
      "#{num} bottles"
    end
  end
end

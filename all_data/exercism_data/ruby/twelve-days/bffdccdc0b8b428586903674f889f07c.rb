class TwelveDaysSong

  NUMS = {
    1 => "first",
    2 => "second",
    3 => "third",
    4 => "fourth",
    5 => "fifth",
    6 => "sixth",
    7 => "seventh",
    8 => "eighth",
    9 => "ninth",
    10 => "tenth",
    11 => "eleventh",
    12 => "twelfth"
  }

  PARTS = {
    1 => " a Partridge in a Pear Tree",
    2 => " two Turtle Doves",
    3 => " three French Hens",
    4 => " four Calling Birds",
    5 => " five Gold Rings",
    6 => " six Geese-a-Laying",
    7 => " seven Swans-a-Swimming",
    8 => " eight Maids-a-Milking",
    9 => " nine Ladies Dancing",
    10 => " ten Lords-a-Leaping",
    11 => " eleven Pipers Piping",
    12 => " twelve Drummers Drumming"
  }

  def initialize()
    
  end

  def verse(num)
    first_line(num) << concat_parts((1..num).to_a.reverse) << ".\n"
  end

  def verses(from,to)
    (from..to).inject([]){|sum, n| sum << verse(n)}.join("\n") + "\n"
  end

  def sing
    verses(1,12)
  end

  private

  def first_line(num)
    "On the #{NUMS[num]} day of Christmas my true love gave to me,"
  end

  def concat_parts(array)
    array.inject([]) do |sum, n|
      and_string = n == array.last && array.size > 1 ? " and" : ""
      sum << and_string + PARTS[n]
    end
      .join(",")
  end


end

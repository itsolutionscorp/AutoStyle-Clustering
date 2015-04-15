class TwelveDaysSong

  GIFTS = {
    1 => "a Partridge in a Pear Tree.\n",
    2 => "two Turtle Doves",
    3 => "three French Hens",
    4 => "four Calling Birds",
    5 => "five Gold Rings",
    6 => "six Geese-a-Laying",
    7 => "seven Swans-a-Swimming",
    8 => "eight Maids-a-Milking",
    9 => "nine Ladies Dancing",
    10 => "ten Lords-a-Leaping",
    11 => "eleven Pipers Piping",
    12 => "twelve Drummers Drumming",
  }

  DAYS = {
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
    12 => "twelfth",
  }

  def sing
    verses(1,12)
  end

  def verses(start,finish)
    song = []
    (start..finish).each do |i|
      song << verse(i) << "\n"
    end
    song.join("")
  end

  def verse(number, words = [])
    if number == 0
      words[-1] = "and " + words[-1] unless words.length == 1
      words.unshift "On the #{DAYS[words.length]} day of Christmas my true love gave to me"
      finished_verse = words.join(", ")
      return finished_verse
    end
    words << GIFTS[number]
    verse(number-1, words)
  end

  private

end

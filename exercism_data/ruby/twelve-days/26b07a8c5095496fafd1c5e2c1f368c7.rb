class TwelveDaysSong

  def verse(days)
    song = verse_beginning(days)
    counter = days

    days.times do |day|
      song << prepare_last_line(counter, days) + gifts[counter]
      counter -= 1
    end

    song + "\n"
  end

  def verses(start, stop)
    (start..stop).each_with_object("") do |i, song|
      song << verse(i) + "\n"
    end
  end

  def sing
    verses(1, 12)
  end

  private

  def verse_beginning(day)
    "On the #{count[day]} day of Christmas my true love gave to me, "
  end

  def prepare_last_line(counter, days)
    if counter == 1 && days != 1
      "and "
    else
      ""
    end
  end

  def count
    {
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
  end

  def gifts
    {
      1 => "a Partridge in a Pear Tree.",
      2 => "two Turtle Doves, ",
      3 => "three French Hens, ",
      4 => "four Calling Birds, ",
      5 => "five Gold Rings, ",
      6 => "six Geese-a-Laying, ",
      7 => "seven Swans-a-Swimming, ",
      8 => "eight Maids-a-Milking, ",
      9 => "nine Ladies Dancing, ",
      10 => "ten Lords-a-Leaping, ",
      11 => "eleven Pipers Piping, ",
      12 => "twelve Drummers Drumming, "
    }
  end

end

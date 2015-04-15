class TwelveDaysSong

  def sing
    verses(1, 12)
  end

  def verses(start, stop)
    (start..stop).each_with_object("") do |i, song|
      song << verse(i) + "\n"
    end
  end

  def verse(days)
    song = verse_beginning(days)
    counter = days

    days.times do
      song << prepare_last_line(counter, days) + gifts[counter - 1]
      song << ", " unless counter == 1
      counter -= 1
    end

    song + "\n"
  end

  private

  def verse_beginning(i)
    "On the #{count[i - 1]} day of Christmas my true love gave to me, "
  end

  def prepare_last_line(counter, days)
    if counter == 1 && days != 1
      "and "
    else
      ""
    end
  end

  def count
    [
      "first",
      "second",
      "third",
      "fourth",
      "fifth",
      "sixth",
      "seventh",
      "eighth",
      "ninth",
      "tenth",
      "eleventh",
      "twelfth"
    ]
  end

  def gifts
    [
      "a Partridge in a Pear Tree.",
      "two Turtle Doves",
      "three French Hens",
      "four Calling Birds",
      "five Gold Rings",
      "six Geese-a-Laying",
      "seven Swans-a-Swimming",
      "eight Maids-a-Milking",
      "nine Ladies Dancing",
      "ten Lords-a-Leaping",
      "eleven Pipers Piping",
      "twelve Drummers Drumming"
    ]
  end

end

class TwelveDaysSong
  def sing
    verses(*GIFTS.keys.minmax)
  end

  def verses(first_verse, last_verse)
    (first_verse..last_verse).map { |n| verse(n) + "\n" }.join
  end

  def verse(number)
    "On the #{ordinal(number)} day of Christmas my true love gave to me, #{gifts(number)}.\n"
  end

  private

  def ordinal(number)
    ORDINALS.fetch(number)
  end

  def gifts(number)
    list_to_sentence number.downto(1).map { |n| GIFTS.fetch(n) }
  end

  def list_to_sentence(collection)
    if collection.length == 1
      collection.first
    else
      collection[0...-1].join(", ") + ", and " + collection[-1]
    end
  end

  GIFTS = {
    1 => "a Partridge in a Pear Tree",
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

  ORDINALS = {
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
end

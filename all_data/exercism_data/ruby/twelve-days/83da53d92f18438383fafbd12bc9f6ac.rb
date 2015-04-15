class TwelveDaysSong
  START = "On the %s day of Christmas my true love gave to me"
  PARTS = [
            "twelve Drummers Drumming",
            "eleven Pipers Piping",
            "ten Lords-a-Leaping",
            "nine Ladies Dancing",
            "eight Maids-a-Milking",
            "seven Swans-a-Swimming",
            "six Geese-a-Laying",
            "five Gold Rings",
            "four Calling Birds",
            "three French Hens",
            "two Turtle Doves",
            "and a Partridge in a Pear Tree.\n"
  ]

  DAYS = [
    "dummy",
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

  def verse(i)
    start = "#{sprintf(START, DAYS[i])}, "
    mid = ((12 - i)..11).map { |j| "#{PARTS[j]}, " }.join
    mid.gsub!("and ", "") if i == 1
    mid.chomp!(", ")
    start << mid
  end

  def verses(a, b)
    (a..b).map { |i| "#{verse(i)}\n" }.join
  end

  def sing
    verses(1, 12)
  end
end

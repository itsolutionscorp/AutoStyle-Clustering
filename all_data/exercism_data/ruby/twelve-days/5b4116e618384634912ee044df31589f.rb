class TwelveDaysSong
  def verse(n_plus_1)
    n = n_plus_1 - 1
    ([intro(n)] + segments(n)).join(", ")
  end

  def sing
    verses(1, 12)
  end

  def verses(start, last = PHRASES.length)
    (start..last).map{ |n| verse(n) }.reduce("") do |text, v|
      text << v << "\n"
    end
  end

  private
  def intro(n)
    "On the #{ORDINAL[n]} day of Christmas my true love gave to me"
  end

  def segments(n)
    segs = n.downto(0).map{ |i| PHRASES[i] }
    return segs if segs.length == 1
    segs[-1] = "and " + segs.last
    segs
  end
  
  ORDINAL = [ "first", "second", "third", "fourth",
              "fifth", "sixth", "seventh", "eighth",
              "ninth", "tenth", "eleventh", "twelfth" ]

  PHRASES = [
    "a Partridge in a Pear Tree.\n",
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

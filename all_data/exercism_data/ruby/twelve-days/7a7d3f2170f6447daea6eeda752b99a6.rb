class TwelveDaysSong
  def sing
    verses(1, 12)
  end

  def verses(start, finish)
    (start..finish).map(&method(:verse)).join("\n") << "\n"
  end

  def verse(n)
    [preamble(n), *list_of_gifts(n)].join(", ") << ".\n"
  end

  private

  def list_of_gifts(n)
    n.downto(1).map {|i| gift(i) }.tap do |ary|
      ary.last.prepend("and ") if ary.length > 1
    end
  end

  def gift(n)
    NUMBERS_AND_GIFTS[n][:gift].dup
  end

  def nth(n)
    NUMBERS_AND_GIFTS[n][:ordinal]
  end

  def preamble(n)
    "On the #{nth(n)} day of Christmas my true love gave to me"
  end

  NUMBERS_AND_GIFTS = {
    1  => { ordinal: "first",    gift: "a Partridge in a Pear Tree" },
    2  => { ordinal: "second",   gift: "two Turtle Doves" },
    3  => { ordinal: "third",    gift: "three French Hens" },
    4  => { ordinal: "fourth",   gift: "four Calling Birds" },
    5  => { ordinal: "fifth",    gift: "five Gold Rings" },
    6  => { ordinal: "sixth",    gift: "six Geese-a-Laying" },
    7  => { ordinal: "seventh",  gift: "seven Swans-a-Swimming" },
    8  => { ordinal: "eighth",   gift: "eight Maids-a-Milking" },
    9  => { ordinal: "ninth",    gift: "nine Ladies Dancing" },
    10 => { ordinal: "tenth",    gift: "ten Lords-a-Leaping" },
    11 => { ordinal: "eleventh", gift: "eleven Pipers Piping" },
    12 => { ordinal: "twelfth",  gift: "twelve Drummers Drumming" }
  }

end

class TwelveDaysSong
  ORDINALS = %w[zeroth first second third fourth fifth sixth seventh eighth ninth tenth eleventh twelfth]
  GIFTS = ["a Partridge in a Pear Tree.", "two Turtle Doves", "three French Hens",
    "four Calling Birds", "five Gold Rings", "six Geese-a-Laying",
    "seven Swans-a-Swimming", "eight Maids-a-Milking", "nine Ladies Dancing",
    "ten Lords-a-Leaping", "eleven Pipers Piping", "twelve Drummers Drumming"]

  def verse(day)
    introduction(day) + gifts(day) + "\n"
  end

  def verses(first, last)
    (first..last).map { |day| verse(day) }.join("\n") + "\n"
  end

  def sing
    verses(1, GIFTS.length)
  end

  private

  def introduction(day)
    "On the #{ORDINALS[day]} day of Christmas my true love gave to me, "
  end

  def gifts(n)
    if n == 1
      GIFTS[0]
    else
      all_gifts = GIFTS[0, n].reverse
      first_gifts = all_gifts[0...-1].join(", ")
      last_gift = all_gifts.last
      [first_gifts, last_gift].join(", and ")
    end
  end
end

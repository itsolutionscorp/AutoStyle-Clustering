class TwelveDaysSong
  def verse n
    "On the #{nth(n)} day of Christmas my true love gave to me, #{count(n)}.\n"
  end

  def verses n, m
    n.upto(m).map { |i| verse i }.join("\n") + "\n"
  end

  def sing
    verses 1, 12
  end

  private

  WORD_NUM = %w(first second third fourth fifth sixth seventh eighth ninth tenth eleventh twelfth)

  PRESENTS = [
    "a Partridge in a Pear Tree",
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

  def nth n
    WORD_NUM[n-1]
  end

  def count n
    if n > 1
      PRESENTS[1..n-1].reverse.join(', ') + ', and '
    end.to_s + PRESENTS[0]
  end

end

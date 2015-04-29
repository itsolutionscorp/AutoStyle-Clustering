class TwelveDaysSong
  def verse(number)
    "#{first_part number}#{join_gifts(gifts number)}.\n"
  end

  def verses(first, last)
    first.upto(last).map { |number| verse number }.join("\n") + "\n"
  end

  def sing
    verses 1, 12
  end

  NUMBER_WORDS = { 1 =>  "first", 
                   2 =>  "second",
                   3 =>  "third",
                   4 =>  "fourth",
                   5 =>  "fifth",
                   6 =>  "sixth",
                   7 =>  "seventh",
                   8 =>  "eighth",
                   9 =>  "ninth",
                   10 => "tenth",
                   11 => "eleventh",
                   12 => "twelfth" }


  GIFTS_BY_DAY = { 1 =>  "a Partridge in a Pear Tree",
                   2 =>  "two Turtle Doves",
                   3 =>  "three French Hens",
                   4 =>  "four Calling Birds",
                   5 =>  "five Gold Rings",
                   6 =>  "six Geese-a-Laying",
                   7 =>  "seven Swans-a-Swimming",
                   8 =>  "eight Maids-a-Milking",
                   9 =>  "nine Ladies Dancing",
                   10 => "ten Lords-a-Leaping",
                   11 => "eleven Pipers Piping",
                   12 => "twelve Drummers Drumming" }                   

  def first_part(number)
    "On the #{NUMBER_WORDS[number]} day of Christmas my true love gave to me, "
  end

  def gifts(number)
    number.downto(1).with_object([]) do |day, presents|
      presents << GIFTS_BY_DAY[day]
    end
  end

  def join_gifts(gifts)
    gifts[-1] = "and #{gifts[-1]}" if gifts.length > 1
    gifts.join ", "
  end
end

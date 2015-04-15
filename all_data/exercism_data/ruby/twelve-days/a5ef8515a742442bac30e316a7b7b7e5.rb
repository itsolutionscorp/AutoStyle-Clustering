class TwelveDaysSong
  def verse(day)
    @day = day
    "#{my_love_gave_to_me} #{all_kinds_of_gifts}.\n"
  end

  def verses(first, last)
    combine first.upto(last).map { |day| verse day }
  end

  def sing
    verses 1, 12
  end

private 
  Phrase = Struct.new(:nth, :gift)

  PHRASES = {  1 => Phrase.new("first",    "a Partridge in a Pear Tree"), 
               2 => Phrase.new("second",   "two Turtle Doves"),
               3 => Phrase.new("third",    "three French Hens"),
               4 => Phrase.new("fourth",   "four Calling Birds"),
               5 => Phrase.new("fifth",    "five Gold Rings"),
               6 => Phrase.new("sixth",    "six Geese-a-Laying"),
               7 => Phrase.new("seventh",  "seven Swans-a-Swimming"),
               8 => Phrase.new("eighth",   "eight Maids-a-Milking"),
               9 => Phrase.new("ninth",    "nine Ladies Dancing"),
              10 => Phrase.new("tenth",    "ten Lords-a-Leaping"),
              11 => Phrase.new("eleventh", "eleven Pipers Piping"), 
              12 => Phrase.new("twelfth",  "twelve Drummers Drumming") }
                

  def my_love_gave_to_me
    "On the #{nth} day of Christmas my true love gave to me,"
  end

  def nth
    PHRASES[@day].nth
  end

  def all_kinds_of_gifts
    concat @day.downto(1).map { |day| gift_of day }
  end

  def gift_of(day)
    PHRASES[day].gift
  end

  def concat(gifts)
    gifts[-1] = "and #{gifts[-1]}" if gifts.length > 1
    gifts.join ", "
  end

  def combine(verses)
    verses.join("\n") + "\n"
  end
end

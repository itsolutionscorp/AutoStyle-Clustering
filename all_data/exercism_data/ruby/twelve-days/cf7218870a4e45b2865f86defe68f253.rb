class TwelveDaysSong
  DAYS = %w(first second third fourth fifth sixth seventh eighth ninth tenth eleventh twelfth)

  VERSES = [
    ["twelve Drummers Drumming, ", 12],
    ["eleven Pipers Piping, ", 11],
    ["ten Lords-a-Leaping, ", 10],
    ["nine Ladies Dancing, ", 9],    
    ["eight Maids-a-Milking, ", 8],      
    ["seven Swans-a-Swimming, ", 7],
    ["six Geese-a-Laying, ", 6],
    ["five Gold Rings, ", 5],
    ["four Calling Birds, ", 4], 
    ["three French Hens, ", 3],
    ["two Turtle Doves, and ", 2],
  ]

  def verse(num)
    s = "On the #{DAYS[num-1]} day of Christmas my true love gave to me, "
    VERSES.each do |phrase, verse_num|
      s << phrase if num >= verse_num
    end
    s << "a Partridge in a Pear Tree.\n"
    s
  end

  def verses(start, stop)
    (start..stop).map { |num| verse(num) }.join("\n") + "\n"
  end

  def sing
    verses(1, 12)
  end
end

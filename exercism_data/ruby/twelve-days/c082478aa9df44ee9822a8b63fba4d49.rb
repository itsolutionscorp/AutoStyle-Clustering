class TwelveDaysSong

  NUMBERS_TO_WORDS = %w(first second third fourth fifth sixth seventh eighth ninth tenth eleventh twelfth)

  OPENING = ["On the "," day of Christmas my true love gave to me, "]

  VERSES = [
    "and a Partridge in a Pear Tree.\n",
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
    "twelve Drummers Drumming",
  ]

  def verse(n)
    song = opening_phrase(n)
    song << (n-1).downto(0).map do |index|
      VERSES[index] 
    end.join(", ")
    remove_and_in_first_verse(song, n)
  end

  def verses(start, finish)
    start.upto(finish).map do |n| 
      verse(n) 
    end.join("\n") << "\n"
  end

  def sing
    verses(1, 12)
  end

  private
  def opening_phrase(n)
    OPENING[0] + NUMBERS_TO_WORDS[n-1] + OPENING[1]
  end

  def remove_and_in_first_verse(song, n)
    song.gsub!(" and", "") if n == 1
    song
  end
  
end

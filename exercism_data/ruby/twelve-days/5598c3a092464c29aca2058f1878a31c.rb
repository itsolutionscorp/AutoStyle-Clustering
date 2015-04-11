class TwelveDaysSong
  GIFTS = [
    'twelve Drummers Drumming,',
    'eleven Pipers Piping,',
    'ten Lords-a-Leaping,',
    'nine Ladies Dancing,',
    'eight Maids-a-Milking,',
    'seven Swans-a-Swimming,',
    'six Geese-a-Laying,',
    'five Gold Rings,',
    'four Calling Birds,',
    'three French Hens,',
    'two Turtle Doves, and',
    'a Partridge in a Pear Tree'
  ]

  NUM_TO_WORD = {
    1  => 'first',
    2  => 'second',
    3  => 'third',
    4  => 'fourth',
    5  => 'fifth',
    6  => 'sixth',
    7  => 'seventh',
    8  => 'eighth',
    9  => 'ninth',
    10 => 'tenth',
    11 => 'eleventh',
    12 => 'twelfth'
  }

  # NB: You can use \ to indicate that any line of Ruby continues on the next line. 
  def verse(n)
    "On the #{NUM_TO_WORD[n]} day of Christmas my true love gave to me, " \
      "#{GIFTS.last(n).join(' ')}.\n"
  end

  def verses(start, finish)
    (start..finish).reduce('') do |song, verse_num|
      song << "#{verse(verse_num)}\n"
    end
  end

  def sing
    verses(1, 12)
  end
end

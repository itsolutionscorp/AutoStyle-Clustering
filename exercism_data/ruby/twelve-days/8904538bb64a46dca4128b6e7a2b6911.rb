class TwelveDaysSong
  VERSES = {
    twelfth:  'twelve Drummers Drumming, ',
    eleventh: 'eleven Pipers Piping, ',
    tenth:    'ten Lords-a-Leaping, ',
    ninth:    'nine Ladies Dancing, ',
    eighth:   'eight Maids-a-Milking, ',
    seventh:  'seven Swans-a-Swimming, ',
    sixth:    'six Geese-a-Laying, ',
    fifth:    'five Gold Rings, ',
    fourth:   'four Calling Birds, ',
    third:    'three French Hens, ',
    second:   'two Turtle Doves, and ',
    first:    'a Partridge in a Pear Tree.'
  }.reverse_each.with_object('').map do |(day, gift), gifts|
    "On the #{day} day of Christmas my true love gave to me,"\
    " #{gifts.prepend gift}\n\n"
  end

  def verse(day)
    VERSES[day - 1].chomp
  end

  def verses(from = 1, to = VERSES.count)
    VERSES[from - 1...to].join
  end
  alias_method :sing, :verses
end

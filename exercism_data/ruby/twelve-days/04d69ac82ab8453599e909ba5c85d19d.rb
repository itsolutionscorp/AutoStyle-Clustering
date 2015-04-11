class TwelveDaysSong
  VERSE_MAP = { 
    1 => ["first", "a Partridge in a Pear Tree"],
    2 => ["second", "two Turtle Doves, and "], 
    3 => ["third","three French Hens, "],
    4 => ["fourth","four Calling Birds, "],
    5 => ["fifth","five Gold Rings, "],
    6 => ["sixth","six Geese-a-Laying, "],
    7 => ["seventh","seven Swans-a-Swimming, "],
    8 => ["eighth","eight Maids-a-Milking, "],
    9 => ["ninth","nine Ladies Dancing, "],
    10 => ["tenth","ten Lords-a-Leaping, "],
    11 => ["eleventh","eleven Pipers Piping, "],
    12 => ["twelfth","twelve Drummers Drumming, "]   
  }
  
  def verse(num)
    verse = "On the #{VERSE_MAP[num][0]} day of Christmas my true love gave to me, #{creatures(num)}.\n"
  end
  
  def verses(start,finish)
    verse = []
    start.upto(finish) do |n| 
      verse << verse(n)
      verse << "\n"
    end   
    verse.join
  end
  
  def sing
    verses(1,12)
  end  
  
  private
  def creatures(num)
    if num == 1      
      VERSE_MAP[num][1]
    else
      VERSE_MAP[num][1]+ creatures(num-1)  
    end
  end  
  
end

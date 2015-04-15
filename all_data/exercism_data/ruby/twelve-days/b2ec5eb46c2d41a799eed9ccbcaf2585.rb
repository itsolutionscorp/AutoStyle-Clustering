class TwelveDaysSong
  LINES = [
    ["first","a Partridge in a Pear Tree"],
    ["second","two Turtle Doves"],
    ["third","three French Hens"],
    ["fourth","four Calling Birds"],
    ["fifth","five Gold Rings"],
    ["sixth","six Geese-a-Laying"],
    ["seventh","seven Swans-a-Swimming"],
    ["eighth","eight Maids-a-Milking"],
    ["ninth","nine Ladies Dancing"],
    ["tenth","ten Lords-a-Leaping"],
    ["eleventh","eleven Pipers Piping"],
    ["twelfth","twelve Drummers Drumming"],
  ]
  
  def sing
    verses 1,12
  end
  
  def verses first, last
    (first..last).map{|number| verse number}.join("\n") + "\n"
  end
  
  def verse number
    "On the #{line_ord number} day of Christmas my true love gave to me, "+
    presents(number)+
    ".\n"
  end
  
  private
  def presents number
    return present number if number == 1
    number.downto(2).map{|n| present n}.join(", ")+", and #{present 1}"
  end
  
  def present number
    LINES[number-1][1]
  end
  
  def line_ord number
    LINES[number-1][0]
  end
end

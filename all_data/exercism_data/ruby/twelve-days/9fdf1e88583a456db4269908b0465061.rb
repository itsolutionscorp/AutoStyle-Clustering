class TwelveDaysSong
  ORDINALS = ["first", "second", "third", "fourth", "fifth", "sixth",
              "seventh", "eighth", "ninth", "tenth", "eleventh", 
              "twelfth"]

  THINGS = ["a Partridge in a Pear Tree.\n",
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
            "twelve Drummers Drumming" ]

  def verse(num)
    start(num) + things(num)
  end
  
  def verses(from, to)
    (from..to).inject("") do |song, num|
      song + verse(num) + "\n"
    end
  end
  
  def sing
    verses(1, 12)
  end
  
private
  def start(num)
    "On the %s day of Christmas my true love gave to me, " % 
      ORDINALS[num-1]
  end
  
  def things(num)
    return THINGS[0] if num == 1
    list = THINGS.take(num).reverse
    list[-1] = "and " + list[-1]
    list.join(", ")
  end
end

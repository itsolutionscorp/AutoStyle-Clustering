class Beer
  def sing(number, to = 0)
    verse_numbers(number, to).each_with_object("") { |i, song| song << verse(i) << "\n" }
  end

  def verse(number)
    if number == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      bottles = "#{number} bottles"
      take_down = "one"
      bottles_left = "#{(number - 1)} bottles"
      if number == 1
        bottles = "1 bottle"
        take_down = "it"
        bottles_left = "no more bottles"
      elsif number == 2
        bottles_left = "1 bottle"
      end
      "#{bottles} of beer on the wall, #{bottles} of beer.\nTake #{take_down} down and pass it around, #{bottles_left} of beer on the wall.\n" 
    end
  end

  def verse_numbers(number, to)
    # returns an array of the verses to be sung
    (to..number).to_a.reverse
  end
end

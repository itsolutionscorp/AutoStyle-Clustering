class Beer

  def verse(number)
    case number
    when 0
      no_more + bottles_on_wall(99)
    when 2
      list_bottles(2) + pass_one + bottles_on_wall(1, "bottle")
    when 1
      list_bottles(1, "bottle") + pass_one("it") + bottles_on_wall("no more")
    else
      list_bottles(number) + pass_one + bottles_on_wall(number - 1)
    end
  end

  def sing(starting_number, ending_number = 0)
    starting_number.downto(ending_number).collect do |number|
      verse(number) 
    end.join("\n") + "\n"
  end

  private

  def list_bottles(number, bottle = "bottles")
    "#{number} #{bottle} of beer on the wall, #{number} #{bottle} of beer.\n"
  end

  def pass_one(number = "one")
    "Take #{number} down and pass it around, "
  end

  def bottles_on_wall(number, bottle = "bottles")
    "#{number} #{bottle} of beer on the wall.\n"
  end

  def no_more
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, "
  end
end

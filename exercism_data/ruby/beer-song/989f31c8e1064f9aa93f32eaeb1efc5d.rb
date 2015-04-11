class Beer

  def verse(number)
    case number
    when 0
      no_more + bottles_on_wall(99)
    when 2
      list_bottles(2) + pass_one + bottles_on_wall(1)
    when 1
      list_bottles(1) + pass_one("it") + bottles_on_wall("no more")
    else
      list_bottles(number) + pass_one + bottles_on_wall(number - 1)
    end
  end

  def sing(start, stop = 0)
    start.downto(stop).collect do |number|
      verse(number) 
    end.join("\n") + "\n"
  end

  private

  def pluralize(number)
    return "bottles" if number.kind_of?(String) || number > 1 || number < 1 

    "bottle"
  end

  def list_bottles(number)
    "#{number} #{pluralize(number)} of beer on the wall, #{number} #{pluralize(number)} of beer.\n"
  end

  def pass_one(number = "one")
    "Take #{number} down and pass it around, "
  end

  def bottles_on_wall(number)
    "#{number} #{pluralize(number)} of beer on the wall.\n"
  end

  def no_more
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, "
  end
end

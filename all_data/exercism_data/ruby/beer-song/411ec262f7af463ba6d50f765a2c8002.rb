Verse = Struct.new(:nr) do
  def to_s
    first_sentence + second_sentence
  end

  def first_sentence
    "#{bottles(nr).capitalize} of beer on the wall, #{bottles(nr)} of beer.\n"
  end

  def second_sentence
    if nr == 0
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "Take #{takedown} down and pass it around, #{bottles(nr - 1)} of beer on the wall.\n"
    end
  end

  def takedown
    nr == 1 ? 'it' : 'one'
  end
  
  def bottles number
    case number
    when 1 then "1 bottle"
    when 0 then "no more bottles"
    else        "#{number} bottles"
    end
  end  
end

class Beer
  def verse nr
    Verse.new(nr).to_s
  end

  def sing from, to = 0
    from.downto(to).map(&method(:verse)).join("\n") + "\n"
  end
end

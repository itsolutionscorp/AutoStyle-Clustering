class Beer
  MANY_BOTTLES = "%d bottles of beer on the wall, %d bottles of beer.\n" <<
                 "Take one down and pass it around, %d %s of beer on the wall.\n"

  ONE_BOTTLE = "1 bottle of beer on the wall, 1 bottle of beer.\n" <<
               "Take it down and pass it around, no more bottles of beer on the wall.\n"

  NO_BOTTLES = "No more bottles of beer on the wall, no more bottles of beer.\n" <<
               "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

  def verse(bottles)
    case bottles
    when 0 then NO_BOTTLES
    when 1 then ONE_BOTTLE
    else
      bottles_left = bottles - 1
      MANY_BOTTLES % [bottles, bottles, bottles_left, bottles_classifier(bottles_left)]
    end
  end

  def sing(from_bottles, to_bottles = 0)
    from_bottles.downto(to_bottles).map do |bottles|
      verse(bottles) + "\n"
    end.join
  end

  private

  def bottles_classifier(bottles)
    bottles == 1 ? 'bottle' : 'bottles'
  end
end

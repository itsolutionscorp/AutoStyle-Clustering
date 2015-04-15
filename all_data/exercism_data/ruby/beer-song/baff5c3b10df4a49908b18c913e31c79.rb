class Beer

  def verse(number)
    answer = number - 1
    if answer == 0
      answer = "no more"
    end

    bottles = "bottles"
    it = "one"
    if number == 1 
      bottles = "bottle"
      it = "it"
    end

    "#{number} #{bottles} of beer on the wall, #{number} #{bottles} of beer.\nTake #{it} down and pass it around, #{answer} bottles of beer on the wall.\n"
  end
end

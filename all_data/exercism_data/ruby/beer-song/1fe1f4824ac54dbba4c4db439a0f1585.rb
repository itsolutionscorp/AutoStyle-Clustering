# The terrible, terrible, not factored version. Have at it!
class Beer
  def sing(start, finish = 0)
    start.downto(finish).collect do |number|
      verse(number) + "\n"
    end.join
  end

  def verse(number)
    answer = number - 1
    bottles = "bottles"
    it = "one"
    if number == 1
      answer = "no more"
      bottles = "bottle"
      it = "it"
    end

    bottles2 = "bottles"
    if number == 2
      bottles2 = "bottle"
    end

    last_line = "Take #{it} down and pass it around"
    if number == 0
      number = "No more"
      bottles = "bottles"
      last_line = "Go to the store and buy some more"
      answer = 99
    end

    "#{number} #{bottles} of beer on the wall, #{number.to_s.downcase} #{bottles} of beer.\n#{last_line}, #{answer} #{bottles2} of beer on the wall.\n"
  end
end

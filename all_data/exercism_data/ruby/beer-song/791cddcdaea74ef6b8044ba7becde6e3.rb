class Beer
  def verse(number)
    if number == 2
      answer_bottles = "#{number - 1} bottle"
      number_bottles = "#{number} bottles"
      one = "one"
      "#{number_bottles} of beer on the wall, #{number_bottles} of beer.\nTake #{one} down and pass it around, #{answer_bottles} of beer on the wall.\n"
    elsif number == 1
      answer_bottles = "no more bottles"
      number_bottles = "1 bottle"
      one = "it"
      "#{number_bottles} of beer on the wall, #{number_bottles} of beer.\nTake #{one} down and pass it around, #{answer_bottles} of beer on the wall.\n"
    elsif number == 0
      nmcaps = "No more bottles"
      nmlow = "no more bottles"
      answer_bottles = "99 bottles"
      "#{nmcaps} of beer on the wall, #{nmlow} of beer.\nGo to the store and buy some more, #{answer_bottles} of beer on the wall.\n"
    else
      answer_bottles = "#{number - 1} bottles"
      number_bottles = "#{number} bottles"
      one = "one"
      "#{number_bottles} of beer on the wall, #{number_bottles} of beer.\nTake #{one} down and pass it around, #{answer_bottles} of beer on the wall.\n"
    end
  end

  def sing(start, finish = 0)
    start.downto(finish).collect do |number|
      verse(number) + "\n"
    end.join
  end
end

class Beer
  def verse(number)
    if number == 2
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    elsif number == 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif number == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      answer_bottles = "#{number - 1} bottles"
      number_bottles = "#{number} bottles"
      "#{number_bottles} of beer on the wall, #{number_bottles} of beer.\nTake one down and pass it around, #{answer_bottles} of beer on the wall.\n"
    end
  end

  def sing(start, finish = 0)
    start.downto(finish).collect do |number|
      verse(number) + "\n"
    end.join
  end
end

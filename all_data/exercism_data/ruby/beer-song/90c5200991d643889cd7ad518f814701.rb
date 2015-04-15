class Beer
  def verse(number)
    @number = number
    beer_verse(@number)
  end

  def beer_verse(number)
    if number == 1 || number == 0
      verse1
    elsif number == 2
      verse2
    else
      reg_verse
    end
  end

  def verse1
    if @number == 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif @number == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end

  def reg_verse
    "#{@number} bottles of beer on the wall, #{@number} bottles of beer.\nTake one down and pass it around, #{@number - 1} bottles of beer on the wall.\n"
  end

  def verse2
   "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
 end

 def sing(number_start, number_finish = 0)
  number_start.downto(number_finish).collect do |number|
    verse(number) + "\n"
  end.join
end
end

class Beer
  def sing(num1, num2 = 0)
    num1.downto(num2).collect {|number| verse(number) + "\n"}.join
  end

  def verse(number)
    if number ==2
      "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    elsif number ==0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elsif number == 1
      "#{number} bottle of beer on the wall, #{number} bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    else
      "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number-1} bottles of beer on the wall.\n"
    end
  end
end

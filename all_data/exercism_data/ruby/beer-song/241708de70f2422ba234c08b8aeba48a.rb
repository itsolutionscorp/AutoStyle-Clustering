class Beer

  def verse(number)
    number_2 = number - 1
    if number == 2
      "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number_2} bottle of beer on the wall.\n"
    elsif number == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elsif number > 1
      "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number_2} bottles of beer on the wall.\n"
    else
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    end

  def sing(start, finish = 0)
    song_1 = ""
    (start).downto(finish) do |number|
      song_1 += verse(number) + "\n"
    end
    song_1
  end
end
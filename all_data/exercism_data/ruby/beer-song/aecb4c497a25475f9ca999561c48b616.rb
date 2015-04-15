class Beer

  def verse(qty)
    if qty > 2
      "#{qty} bottles of beer on the wall, #{qty} bottles of beer.\nTake one down and pass it around, #{qty - 1} bottles of beer on the wall.\n"
    elsif qty == 2
      "#{qty} bottles of beer on the wall, #{qty} bottles of beer.\nTake one down and pass it around, #{qty - 1} bottle of beer on the wall.\n"
    elsif qty == 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif qty == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end

  def sing(start, final = 0)
    song = ""
    start.downto(final) {|number| song << verse(number) + "\n"}
    song
  end

end

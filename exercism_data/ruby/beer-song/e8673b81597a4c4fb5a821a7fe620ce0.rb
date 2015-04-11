class Beer
  def sing(number, to = 0)
    verses(number, to).inject("") do |song, i|
      song << verse(i) << "\n"
      song
    end
  end

  def verse(number)
    if number == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elsif number == 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif number == 2
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    else
      "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number-1} bottles of beer on the wall.\n" 
    end
  end

  def verses(number, to)
    # returns an array of the verses to be sung
    (to..number).to_a.reverse
  end
end

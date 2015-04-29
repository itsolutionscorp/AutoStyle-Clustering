class Beer
  def sing(start, finish = 0)
    start.downto(finish).inject("") do |beer_song, number|
      beer_song += (verse(number) + "\n")
    end
  end
  
  def verse(number)
    if number == 2
      return "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    elsif number == 1 
      return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif number == 0
      return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"  
    elsif (3..99).to_a.include?(number)
      return "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number -1} bottles of beer on the wall.\n"
    end
  end 
end

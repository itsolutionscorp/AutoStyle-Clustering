class BeerSong
  def verse(num_of_bottles)
    case num_of_bottles
    when 0 
      verse_0
    when 1 
      verse_1
    when 2 
      verse_2
    else
      verse_n(num_of_bottles)
    end
  end
  
  def verses(max, min)
    max.downto(min).each_with_object("") do |num_of_bottles, song| 
      song << verse(num_of_bottles)
      song << "\n"
    end
  end
  
  def sing
    verses(99, 0)
  end
  
  private
  
  def verse_0
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end
  
  def verse_1
    "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
  end
  
  def verse_2
    "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
  end
  
  def verse_n(n)
    "#{n} bottles of beer on the wall, #{n} bottles of beer.\nTake one down and pass it around, #{n-1} bottles of beer on the wall.\n"
  end
end

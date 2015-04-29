class BeerSong

  def initialize
  end

  def verse(num)

    if num > 2
      verse = "#{num} bottles of beer on the wall, #{num} bottles of beer.\n" \
      "Take one down and pass it around, #{num-1} bottles of beer on the wall.\n"
    elsif num == 1
      verse = "1 bottle of beer on the wall, 1 bottle of beer.\n" \
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    elsif num == 2
      verse = "2 bottles of beer on the wall, 2 bottles of beer.\n" \
      "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    elsif num == 0
      verse = "No more bottles of beer on the wall, no more bottles of beer.\n" \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end     
      
  end

  def verses(first,last)
    verses = ""
    until first < last
      verses << verse(first) << "\n"
      first-=1
    end
    verses
  end

  def sing
    verses(99,0)
  end

end

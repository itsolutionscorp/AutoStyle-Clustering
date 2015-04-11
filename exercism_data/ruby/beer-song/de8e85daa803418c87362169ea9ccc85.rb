class BeerSong

  GENERIC_VERSE = "N bottles of beer on the wall, N bottles of beer.\n\
Take one down and pass it around, M bottles of beer on the wall.\n"

  VERSE_FOR_0 = "No more bottles of beer on the wall, no more bottles of beer.\n\
Go to the store and buy some more, 99 bottles of beer on the wall.\n"

  def verse(number)
    return VERSE_FOR_0 if (number == 0)
    a_verse = GENERIC_VERSE.gsub(/N/, number.to_s).gsub(/M/, (number - 1).to_s)
    a_verse = a_verse.gsub(/1 bottles/, "1 bottle") if (number < 3)
    a_verse = a_verse.gsub(/one/, "it").gsub(/0 bottles/, "no more bottles") if (number == 1)
    a_verse
  end

  def verses(m,n=0)
    verses = ""
    m.downto(n) do |i|
      verses += verse(i)
      verses += "\n"
    end
    verses
  end
  
  def sing
    verses(99,0)
  end  
  
end

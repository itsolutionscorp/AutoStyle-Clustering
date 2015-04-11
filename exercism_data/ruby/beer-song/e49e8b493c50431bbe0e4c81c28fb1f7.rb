class BeerSong
  def verse(number)
    case number
    when 0
      verse_0
    when 1
      verse_1
    when 2
      verse_2
    else
    "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number - 1} bottles of beer on the wall.\n"
    end
  end

  def verses(bottles_before, bottles_after)
    bottles_before.downto(bottles_after).map { |number| verse(number) + "\n" }.join
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
end

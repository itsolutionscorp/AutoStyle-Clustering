class BeerSong
  def verse(bottles)
    if bottles == 0
      return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elsif bottles == 1
      return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif bottles == 2
      return "#{bottles} bottles of beer on the wall, #{bottles} bottles of beer.\nTake one down and pass it around, #{bottles - 1} bottle of beer on the wall.\n"
    else
      return "#{bottles} bottles of beer on the wall, #{bottles} bottles of beer.\nTake one down and pass it around, #{bottles - 1} bottles of beer on the wall.\n"
    end
  end

  def verses(start, finish)
    ans = ""
    while start > (finish - 1)
      ans += "#{self.verse(start)}\n"
      start -= 1
    end
    ans
  end

  def sing
    self.verses(99, 0)
  end
end

class BeerSong
  def verse(l)
    return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n" if l == 0
    return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n" if l == 1
    "#{l} bottles of beer on the wall, #{l} bottles of beer.\nTake one down and pass it around, #{l-1} bottle#{'s' if l-1 > 1} of beer on the wall.\n"
  end

  def verses(l1, l2)
    (l2..l1).to_a.reverse.map { |l| verse(l) }.join("\n") << "\n"
  end

  def sing
    verses(99, 0)
  end
end

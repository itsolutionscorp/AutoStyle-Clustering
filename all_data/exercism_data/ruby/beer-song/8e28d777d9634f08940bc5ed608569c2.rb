class Beer

 VERSES = {
     0 => "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n",
     1 => "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n",
     2 => "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
 }

  def sing(start, last = 0)
    (last..start).map do |bottles|
      "#{ verse(bottles) }\n"
    end.reverse.join
  end

  def verse(bottles)
    VERSES.fetch(bottles) do |left|
      "#{left} bottles of beer on the wall, #{left} bottles of beer.\nTake one down and pass it around, #{left - 1} bottles of beer on the wall.\n"
    end
  end
end

class BeerSong

  def verse vnum
    return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n" if vnum == 0
    vnum == 1 ? plural = ["", "it"] : plural = ["s", "one"]
    vnum == 1 ? bottles_left = "no more bottles" : \
    vnum == 2 ? bottles_left = "#{vnum - 1} bottle" : bottles_left = "#{vnum - 1} bottles"
    "#{vnum} bottle#{plural[0]} of beer on the wall, #{vnum} bottle#{plural[0]} of beer.\nTake #{plural[1]} down and pass it around, #{bottles_left} of beer on the wall.\n"
  end

  def verses vnuma, vnumb
    (vnumb..vnuma).map {|x| verse(x) + "\n"}.reverse.join
  end

  def sing
    verses(99, 0)
  end

end

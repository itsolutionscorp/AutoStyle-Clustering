class BeerSong
  def verse times
    case t = times.to_i # only accept number
    when 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    when 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    else
      bottle_or_bottles = t == 2 ? 'bottle' : 'bottles'
      "#{t} bottles of beer on the wall, #{t} bottles of beer.\nTake one down and pass it around, #{t - 1} #{bottle_or_bottles} of beer on the wall.\n"
    end
  end

  def verses from, to
    from.downto(to).map { |i| verse i }.join("\n") + "\n"
  end

  def sing
    verses(99, 0)
  end
end

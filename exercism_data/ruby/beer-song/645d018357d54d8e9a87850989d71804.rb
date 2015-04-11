class BeerSong
  def base(n)
    if n.zero?
      "No more bottles of beer on the wall, no more bottles of beer.\n" +
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{n} bottle#{n > 1 ? 's' : ''} of beer on the wall, " +
      "#{n} bottle#{n > 1 ? 's' : ''} of beer.\nTake " +
      "#{n == 1 ? 'it' : 'one'} down and pass it around, " +
      "#{n == 1 ? 'no more' : n-1} bottle#{n == 2 ? '' : 's'} " +
      "of beer on the wall.\n"
    end
  end

  def verse(n)
    base(n)
  end

  def verses(x, y)
    (y..x).map { |z| base z }.reverse.join
  end

  def sing
    verses(99, 0)
  end
end

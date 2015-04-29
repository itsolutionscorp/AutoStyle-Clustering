class BeerSong
  def verse n
    if n == 0
      "No more bottles of beer on the wall, no more bottles of beer.\n" \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{n} #{bottles n} of beer on the wall," \
      " #{n} #{bottles n} of beer.\n" \
      "Take #{n == 1 ? 'it' : 'one'} down and pass it around," \
      " #{n == 1 ? 'no more' : n - 1} #{bottles n - 1}" \
      " of beer on the wall.\n"
    end
  end

  def verses a, b
    low, high = [a, b].sort
    [*low..high].reverse.each_with_object '' do |v, memo|
      memo << "#{verse v}\n"
    end
  end

  def sing
    verses(99, 0)
  end

  private

  def bottles n
    n == 1 ? 'bottle' : 'bottles'
  end
end

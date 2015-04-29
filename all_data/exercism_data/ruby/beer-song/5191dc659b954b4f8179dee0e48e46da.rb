class BeerSong
  def sing
    verses 99, 0
  end

  def verses fst, lst
    fst.downto(lst).reduce("") { |r, n| r + (verse n) + "\n" }
  end

  def verse n
    n > 0 ? "#{n} #{bottles n} of beer on the wall, #{n} #{bottles n} of beer.\n" +
        "Take #{one n} down and pass it around, #{more n} #{bottles n-1} of beer on the wall.\n" :
        "No more bottles of beer on the wall, no more bottles of beer.\n" \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def bottles n
    n == 1 ? "bottle" : "bottles"
  end

  def more n
    n-1 == 0 ? "no more" : n-1
  end

  def one n
    n == 1 ? "it" : "one"
  end

end

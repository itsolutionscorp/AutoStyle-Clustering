class BeerSong
  @@v = "%s bottle%s of beer on the wall, %s bottle%s of beer.
Take %s down and pass it around, %s bottle%s of beer on the wall.
"

@@v0 = "No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
"

  def verse(i)
    if i == 0
      @@v0
    else
      c1 = i
      plural_s = i == 1 ? "" : "s"
      w = i == 1 ? "it" : "one"
      c2 = i - 1 == 0 ? "no more" : i - 1
      plural_s2 = i - 1 == 1 ? "" : "s"
      sprintf(@@v, c1, plural_s, c1, plural_s, w, c2, plural_s2)
    end
  end

  def verses(i, j)
    s = [i, j].min
    l = [i, j].max
    l.downto(s).map { |x| verse(x) }.join("\n") << "\n"
  end

  def sing
    verses(99, 0)
  end
end

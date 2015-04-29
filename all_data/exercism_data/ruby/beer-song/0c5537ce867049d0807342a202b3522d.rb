class BeerSong

  def verses(first, last)
    first.downto(last).map { |n| verse(n) + "\n" }.join
  end

  def verse(n)
    return verse_0 if n == 0
    first, pronoun, last = bottles(n)
    "#{first} of beer on the wall, #{first} of beer.\nTake #{pronoun} down and pass it around, #{last} of beer on the wall.\n"
  end

  def sing
    verses(99, 0)
  end

  private
  def bottles(count)
    first = "#{count} bottles"
    pronoun = "one"
    last = "#{count - 1} bottles"
    if count == 2
      last = "1 bottle"
    elsif count == 1
      first = "1 bottle"
      pronoun = "it"
      last = "no more bottles"
    end
    [first, pronoun, last]
  end

  def verse_0
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end
end

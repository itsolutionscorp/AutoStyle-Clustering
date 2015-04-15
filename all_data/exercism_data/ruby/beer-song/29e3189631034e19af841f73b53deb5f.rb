class BeerSong

  def verse(index)
    first_sentence(index) + second_sentence(index)
  end

  def sing
    verses(99,0)
  end

  def verses(start, stop)
    start.downto(stop).reduce("") {|song,index| song + verse(index) + "\n" }
  end

  private

  def n_bottles(amount)
    return "no more bottles" if amount == 0
    return "1 bottle" if amount == 1
    "%d bottles" % amount
  end

  def n_take(amount)
    return "it" if amount == 1
    "one"
  end

  def first_sentence(amount)
    bottles = n_bottles(amount)
    "%s of beer on the wall, %s of beer.\n" % [bottles.capitalize, bottles]
  end

  def second_sentence(amount)
    return "Take %s down and pass it around, %s of beer on the wall.\n" %
      [n_take(amount), n_bottles(amount-1)] if amount > 0
    "Go to the store and buy some more, %s of beer on the wall.\n" % n_bottles(99)
  end

end

p BeerSong.new.verses(8,6)

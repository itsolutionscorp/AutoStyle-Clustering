class Beer
  attr_accessor :bottles_of_beer

  def sing(starting, ending = 0)
    num_bottles = starting

    verse_collector = ""
    until num_bottles == ending-1
      verse_collector << compose_verse(num_bottles)
      num_bottles -= 1
    end
    verse_collector
  end

  def compose_verse(num_bottles)
    verse(num_bottles) + "\n"
  end

  def verse(bottles_of_beer)
    @bottles_of_beer = bottles_of_beer

    return no_more_verse if bottles_of_beer == 0
    return song if bottles_of_beer > 0
  end

  def plurality(num)
    return "#{num} bottles of beer" if num > 1
    return "#{num} bottle of beer" if num == 1
    return "no more bottles of beer" if num == 0
  end

  def no_more_verse
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def song
    num = "one" if bottles_of_beer > 1
    num = 'it' if bottles_of_beer == 1

    plurality(bottles_of_beer) + " on the wall, " + plurality(bottles_of_beer) + ".\nTake " + num + " down and pass it around, " + plurality(bottles_of_beer-1) + " on the wall.\n"
  end
end

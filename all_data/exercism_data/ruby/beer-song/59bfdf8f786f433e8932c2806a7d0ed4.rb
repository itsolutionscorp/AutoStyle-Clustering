class BeerSong
  def verse(number)
    klass = if number > 1
      ManyBottleVerse
    elsif number == 1
      OneBottleVerse
    elsif number == 0
      NoMoreBottleVerse
    end

    klass.new(number).verse
  end

  def verses(first, last)
    first.downto(last).map do |n|
      verse(n) + "\n"
    end.join
  end

  def sing
    verses(99, 0)
  end
end

class BottleVerse < Struct.new(:number)
  def bottleize(num)
    if num > 1
      "#{num} bottles"
    elsif num == 1
      "1 bottle"
    elsif num == 0
      "no more bottles"
    end
  end

  def initial
    "#{bottleize(number)} of beer on the wall, #{bottleize(number)} of beer.\n".capitalize
  end
end

class ManyBottleVerse < BottleVerse
  def verse
    initial + "Take one down and pass it around, #{bottleize(number - 1)} of beer on the wall.\n"
  end
end

class OneBottleVerse < BottleVerse
  def verse
    initial + "Take it down and pass it around, no more bottles of beer on the wall.\n"
  end
end

class NoMoreBottleVerse < BottleVerse
  def verse
    initial + "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end
end

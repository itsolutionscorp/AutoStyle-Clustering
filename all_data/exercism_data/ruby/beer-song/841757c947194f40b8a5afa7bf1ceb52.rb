class BeerSong
  def verse(num)
    Bottler.new(num).bottle.verse
  end

  def sing
    verses(99,0)
  end

  def verses(start, finish)
    start.downto(finish).reduce('') { |result, n| result += "#{verse(n)}\n" }
  end
end

class  Bottler
  attr_reader :bottle

  def initialize(num)
    bottle = { 0 => Zero, 1 => First, 2 => Second }[num] || Basic
    @bottle = bottle.new num
  end
end

class Verse
  def initialize(n)
    @text = ''
  end

  def verse
    first_line + second_line
  end

  private

  def first_line
    "#@amount #@b1 of beer on the wall, #@amount #@b1 of beer.\n".capitalize
  end

  def part2(n)
    ", #{n} #@b2 of beer on the wall.\n"
  end
end

class Zero < Verse
  def initialize(n = 0)
    @amount = "no more"
    @b1, @b2 = "bottles", "bottles"
  end

  private

  def second_line
    "Go to the store and buy some more#{part2(99)}"
  end
end

class First < Verse
  def initialize(n = 1)
    @amount = n
    @b1, @b2 = "bottle", "bottles"
  end

  private

  def second_line
    "Take it down and pass it around#{part2("no more")}"
  end
end


class Basic < Verse
  def initialize(n)
    @amount = n
    @b1, @b2 = "bottles", "bottles"
  end

  private

  def second_line
    "Take one down and pass it around#{part2(@amount-1)}"
  end
end

class Second < Basic
  def initialize(n = 2)
    @amount = n
    @b1, @b2 = "bottles", "bottle"
  end
end

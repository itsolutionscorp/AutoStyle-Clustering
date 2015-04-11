class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(upper_bound, lower_bound)
    upper_bound.downto(lower_bound).collect {|n| verse(n) + "\n"}.join
  end

  def verse(number)
    BeerSongVerse.for(number).to_s
  end
end

class BeerSongVerse
  attr_reader :number
  def self.for(number)
    begin
      Object.const_get("BeerSongVerse#{number}")
    rescue NameError
      self
    end.new(number)
  end

  def initialize(number)
    @number = number
  end

  def to_s
    "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number-1} bottles of beer on the wall.\n"
  end
end

class BeerSongVerse0 < BeerSongVerse
  def to_s
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end
end

class BeerSongVerse1 < BeerSongVerse
  def to_s
    "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
  end
end

class BeerSongVerse2 < BeerSongVerse
  def to_s
    "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
  end
end

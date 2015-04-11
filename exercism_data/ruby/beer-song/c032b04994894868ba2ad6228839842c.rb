class BeerSong
  attr_reader :verse_number

  def verse number
    Verse.new( number ).sing
  end

  def verses start_verse, end_verse
    start_verse.downto( end_verse ).map { |number| verse( number )+ ("\n") }.join
  end

  def sing
    verses 99, 0
  end
end

class Verse
  attr_accessor :number

  def initialize number
    @number = number
  end

  def sing
    klass = case number
      when 0 then NoBottleVerse
      when 1 then LastBottleVerse
      else        ManyBottlesVerse
    end

    klass.new( number ).sing
  end
end

class BeerSong::BaseVerse
  attr_accessor :bottles

  def initialize number
    @bottles = number
  end

  def sing
    prelude + action
  end

private
  def prelude
    "#{bottles_ammount.capitalize} of beer on the wall, #{bottles_ammount} of beer.\n"
  end
end

class NoBottleVerse < BeerSong::BaseVerse
private
  def action
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def bottles_ammount
    "no more bottles"
  end
end

class LastBottleVerse < BeerSong::BaseVerse
private
  def action
    "Take it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def bottles_ammount
    "#{bottles} bottle"
  end
end

class ManyBottlesVerse < BeerSong::BaseVerse
private
  def action
    "Take one down and pass it around, #{bottles_ammount( bottles-1 )} of beer on the wall.\n"
  end

  def bottles_ammount ammount = bottles
    "#{ammount} bottle#{'s' if ammount > 1}"
  end

end
      

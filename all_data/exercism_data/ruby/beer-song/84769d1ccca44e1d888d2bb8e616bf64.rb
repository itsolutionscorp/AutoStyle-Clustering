ZERO = lambda do |bottle|
    "No more bottles of beer on the wall, no more bottles of beer.\n" +
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end

ONE = lambda do |bottle|
    "1 bottle of beer on the wall, 1 bottle of beer.\n" +
    "Take it down and pass it around, no more bottles of beer on the wall.\n"
    end

TWO = lambda do |bottle|
    "2 bottles of beer on the wall, 2 bottles of beer.\n" +
    "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    end

STANDARD = lambda do |bottle|
    "#{bottle.number} bottles of beer on the wall, #{bottle.number} bottles of beer.\n" +
    "Take one down and pass it around, #{bottle.number - 1} bottles of beer on the wall.\n"
  end

class Beer
  attr_reader :number, :verse
  def initialize number, &verse
    @number = number
    @verse = verse
  end

  def output
    @verse.call self
  end
end

class BeerSong

  def verse number
    beer = fill_bottle(number).output
  end

  def verses finish, start
    (finish).downto(start).map {|verse_number| verse verse_number }.join("\n") + "\n"
  end

  def sing
    verses 99, 0
  end

  def fill_bottle number
    case number
    when 0 then Beer.new number, &ZERO
    when 1 then Beer.new number, &ONE
    when 2 then Beer.new number, &TWO
    when 3..99 then Beer.new number, &STANDARD
    end
  end
end

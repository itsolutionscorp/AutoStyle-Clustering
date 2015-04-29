class Beer

  def verse(bottle_count)
    BeerVerse.new(bottle_count).to_s
  end

  def sing(from, to = 0)
    return '' if from < to
    "#{verse(from)}\n" + sing(from - 1, to)
  end
end

class BeerVerse

  def initialize(bottle_count)
    @bottle_count = bottle_count
  end

  def to_s
    case bottle_count
    when 0
      zero_bottle_verse
    when 1
      one_bottle_verse
    when 2
      two_bottle_verse
    else
      many_bottle_verse(bottle_count)
    end
  end


  private
  
  attr_reader :bottle_count

  def zero_bottle_verse
    "No more bottles of beer on the wall, no more bottles of beer.\n"\
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def one_bottle_verse
    "1 bottle of beer on the wall, 1 bottle of beer.\n"\
    "Take it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def two_bottle_verse
    "2 bottles of beer on the wall, 2 bottles of beer.\n"\
    "Take one down and pass it around, 1 bottle of beer on the wall.\n"
  end

  def many_bottle_verse(bottle_count)
    "#{bottle_count} bottles of beer on the wall, #{bottle_count} bottles of beer.\n"\
    "Take one down and pass it around, #{bottle_count - 1} bottles of beer on the wall.\n"
  end
end

class Beer
  def initialize
    @bottles = []
  end

  def verse(which)
    which = Bottle.new(which) unless @bottles.include?(which)
    which.lyricize
  end

  def sing(start_count, end_count = 0)
    start_count.downto(end_count) { |which| @bottles << Bottle.new(which) }
    @bottles.map { |bottle| "#{verse(bottle)}\n" }.join
  end
end

class Bottle
  attr_accessor :count

  def initialize(which)
    @count = which
  end

  def lyricize
    Lyric.new(self).create_lyric
  end

  def count_to_bottles
    case @count
    when 0 then 'no more bottles'
    when 1 then '1 bottle'
    else "#{@count} bottles"
    end
  end
end

class Lyric
  def initialize(bottle)
    @bottle = bottle
  end

  def create_lyric
    [first_part, last_part].join
  end

  private

  def first_part
    "#{@bottle.count_to_bottles} of beer on the wall, #{@bottle.count_to_bottles} of beer.\n".capitalize
  end

  def last_part
    part = ''
    if @bottle.count.zero?
      part += 'Go to the store and buy some more, '
      @bottle.count = 99
    elsif @bottle.count == 1
      part += 'Take it down and pass it around, '
      @bottle.count -= 1
    else
      part += 'Take one down and pass it around, '
      @bottle.count -= 1
    end
    part += "#{@bottle.count_to_bottles} of beer on the wall.\n"
    part.capitalize
  end
end

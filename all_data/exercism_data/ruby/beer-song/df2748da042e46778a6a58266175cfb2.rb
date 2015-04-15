class Singer
  attr_reader :lyrics

  def initialize
    @lyrics = ''
  end

  def sing_line(line)
    @lyrics += line + "\n"
  end

  def sing_lines(lines)
    lines.each { |line| sing_line line }
  end

  def self.perform(&block)
    singer = Singer.new
    block.call(singer)
    singer.lyrics
  end
end

class Beer
  def verse(num)
    Singer.perform do |singer|
      singer.sing_lines verse_lines(num)
    end
  end

  def sing(first, last = 0)
    Singer.perform do |singer|
      first.downto(last) do |num|
        singer.sing_lines verse_lines(num)
        singer.sing_line ''
      end
    end
  end

  private

  def verse_lines(num)
    bottles_of_beer = BottlesOfBeer.new(num)
    [
      "#{bottles_of_beer.to_s.capitalize} on the wall, " +
        "#{bottles_of_beer}.",

      if bottles_of_beer.no_more?
        "Go to the store and buy some more, " +
          "#{BottlesOfBeer.new(99)} on the wall."
      else
        "Take #{bottles_of_beer.one} down and pass it around, " +
          "#{bottles_of_beer - 1} on the wall."
      end
    ]
  end
end

class BottlesOfBeer
  def initialize(num)
    @num = num
  end

  def to_s
    case @num
    when 0 then 'no more bottles of beer'
    when 1 then '1 bottle of beer'
    else "#{@num} bottles of beer"
    end
  end

  def one
    @num == 1 ? 'it' : 'one'
  end

  def no_more?
    @num == 0
  end

  def -(delta)
    BottlesOfBeer.new(@num - delta)
  end
end

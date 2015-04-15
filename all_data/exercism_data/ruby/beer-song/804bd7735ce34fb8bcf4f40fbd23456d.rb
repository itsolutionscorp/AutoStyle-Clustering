class Beer
  def sing(first, last = 0)
    first.downto(last).inject("") do |song, n|
      song << verse(n) << "\n"
    end
  end

  def verse(number)
    Verse.new(number).sing
  end
end

class Verse
  def initialize(number)
    @bottles = Bottles.new(number.to_int)
  end

  def sing
    <<-eos
#{bottles.to_s.capitalize} of beer on the wall, #{bottles} of beer.
#{act_on_the_beer}, #{bottles - 1} of beer on the wall.
    eos
  end

  private
  attr_reader :bottles

  def act_on_the_beer
    return "Go to the store and buy some more" if bottles.zero?
    "Take #{one} down and pass it around"
  end

  def one
    bottles > 1 ? "one" : "it"
  end
end

require 'delegate'
class Bottles < SimpleDelegator

  attr_reader :num
  def initialize(num)
    super @num = num
  end

  def bottles
    case num
    when 0; "no more bottles"
    when 1; "1 bottle"
    else; "#{num} bottles"
    end
  end
  alias :to_s :bottles

  def -(other)
    return self.class.new 99 if num.zero?
    self.class.new super
  end
end

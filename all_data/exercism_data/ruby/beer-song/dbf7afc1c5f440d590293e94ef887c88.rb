class Beer
  def verse(number)
    singer = Vocal.new(number)
    singer.sing_it
  end

  def sing(first, last=0)
    lyrics = Array.new
    first.downto(last).each { |n| lyrics << verse(n) << "\n" }
    lyrics.join
  end
end

class Vocal
  attr_reader :bottles_of_beer

  def initialize(bottles)
    bottles = 99 if bottles > 99
    @bottles_of_beer = WallOfBeer.new(bottles)
  end

  def sing_it
    [stanza_1, stanza_2, stanza_3, stanza_4].join
  end

  private

  def stanza_1
    "#{bottles_of_beer} on the wall, ".capitalize
  end

  def stanza_2
    "#{bottles_of_beer}.\n"
  end

  def stanza_3
    check_beer_status_and_behave_accordingly
  end

  def stanza_4
    "#{bottles_of_beer} on the wall.\n"
  end

  def check_beer_status_and_behave_accordingly
    if bottles_of_beer.stock_level == 0
      bottles_of_beer.go_to_the_store
    elsif bottles_of_beer.stock_level == 1
      bottles_of_beer.take_it_down
    else
      bottles_of_beer.take_one_down
    end
  end
end

class WallOfBeer < Struct.new(:stock_level)

  def to_s
    return "no more bottles of beer" if stock_level == 0
    return "1 bottle of beer" if stock_level == 1
    "#{stock_level} bottles of beer"
  end

  def go_to_the_store
    restock
    "Go to the store and buy some more, "
  end

  def take_it_down
    remove_a_beer
    "Take it down and pass it around, "
  end

  def take_one_down
    remove_a_beer
    "Take one down and pass it around, "
  end

  private

  def restock
    self.stock_level = 99
  end

  def remove_a_beer
    self.stock_level -= 1
  end
end

class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(upper_bound, lower_bound)
    upper_bound.downto(lower_bound).map {|i| verse(i)}.join("\n") << "\n"
  end

  def verse(i)
    beer_count = BeerCount.new(i)
    "#{beer_count} of beer on the wall, ".capitalize +
    "#{beer_count} of beer.\n" +
    "#{beer_count.action}, " +
    "#{beer_count.next} of beer on the wall.\n"
  end
end

class BeerCount
  attr_reader :i

  def initialize(i)
    @i = i
  end

  def to_s
    "#{name} #{container}"
  end

  def action
    i == 0 ? buy_more : drink
  end

  def next
    self.class.new((i - 1) % 100)
  end

  private

  def name
    i == 0 ? 'no more' : i.to_s
  end

  def container
    i == 1 ? 'bottle' : 'bottles'
  end

  def buy_more
    "Go to the store and buy some more"
  end

  def drink
    "Take #{pronoun} down and pass it around"
  end

  def pronoun
    i == 1 ? 'it' : 'one'
  end
end

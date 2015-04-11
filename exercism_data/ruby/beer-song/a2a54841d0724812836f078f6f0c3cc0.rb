class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(upper_bound, lower_bound)
    upper_bound.downto(lower_bound).map {|i| verse(i) << "\n"}.join
  end

  def verse(i)
    beer_count = BeerCount.new(i)
    "#{beer_count.to_s.capitalize} of beer on the wall, " +
    "#{beer_count} of beer.\n" +
    "#{beer_count.action}, " +
    "#{beer_count.next} of beer on the wall.\n"
  end
end

require 'delegate'

class BeerCount < SimpleDelegator
  def to_s
    "#{how_many} #{container}"
  end

  def action
    self == 0 ? buy_more : drink
  end

  def next
    self.class.new((self - 1) % 100)
  end

  private

  def how_many
    self == 0 ? 'no more' : __getobj__
  end

  def container
    self == 1 ? 'bottle' : 'bottles'
  end

  def buy_more
    "Go to the store and buy some more"
  end

  def drink
    "Take #{pronoun} down and pass it around"
  end

  def pronoun
    self == 1 ? 'it' : 'one'
  end
end

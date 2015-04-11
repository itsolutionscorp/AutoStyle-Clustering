class Beer
  def sing(high, low=0)
    high.downto(low).map { |n| "#{verse(n)}\n" }.join
  end

  def verse(n)
    Verse.recite(n)
  end
end

class Verse
  def self.recite(n)
    @reader ||= new
    @reader.recite(n)
  end

  def recite(n)
    challenge(n) + "\n" + response(n) + "\n"
  end

  private
  def challenge(n)
    "#{wall_state(n)}, #{shelf_state(n).downcase}."
  end

  def response(n)
    nc      = counter.next_count(n)
    "#{action(n)}, #{wall_state(nc).downcase}."
  end

  def counter
    BeerCounter
  end

  def shelf_state(n)
    "#{counter.count(n)} #{noun(n)} of beer"
  end

  def wall_state(n)
    "#{shelf_state(n)} on the wall"
  end

  def action(n)
    if n.zero?
      'Go to the store and buy some more'
    else
      "Take #{pronoun(n)} down and pass it around"
    end
  end

  def noun(n)
    n == 1 ? 'bottle' : 'bottles'
  end

  def pronoun(n)
    n == 1 ? 'it' : 'one'
  end
end

class BeerCounter
  MAX_BEERS = 99
  MIN_BEERS = 0

  def self.count(n)
    (n.to_i == MIN_BEERS) ? 'No more' : n
  end

  def self.next_count(n)
    nc = (n.to_i == MIN_BEERS) ? MAX_BEERS : (n - 1)

    count(nc)
  end
end

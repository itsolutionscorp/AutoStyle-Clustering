class Beer
  def sing(high, low=0)
    high.downto(low).map { |n| "#{verse(n)}\n" }.join
  end

  def verse(n)
    songwriter.challenge(n) + "\n" + songwriter.response(n) + "\n"
  end

  private
  def songwriter
    @songwriter ||= WordSmith.new
  end
end

class WordSmith
  def challenge(n)
    "#{wall_state(n)}, #{shelf_state(n).downcase}."
  end

  def response(n)
    nc      = counter.next_count(n)
    "#{action(n)}, #{wall_state(nc).downcase}."
  end

  private
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
    n.to_i == 1 ? 'bottle' : 'bottles'
  end

  def pronoun(n)
    n.to_i == 1 ? 'it' : 'one'
  end
end

class BeerCounter
  def self.count(n)
    n.to_i.zero? ? 'No more' : n.to_s
  end

  def self.next_count(n)
    nc = (n.zero? ? 99 : (n - 1))
    count(nc)
  end
end

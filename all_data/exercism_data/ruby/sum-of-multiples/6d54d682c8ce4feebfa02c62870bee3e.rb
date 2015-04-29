require 'set'

class SumOfMultiples
  def initialize(*args)
    @multiples = args
  end

  def to(n)
    @multiples
      .reduce(Set.new) { |set, x| set + (x...n).step(x) }
      .reduce(0, &:+)
  end

  def self.to(n)
    SumOfMultiples.new(3,5).to(n)
  end
end

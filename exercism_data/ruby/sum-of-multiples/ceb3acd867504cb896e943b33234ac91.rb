class SumOfMultiples
  @divs = [3, 5]
  class << self
    attr_reader :divs

    def to(n, divs = divs)
      multiples = (2...n).each_with_object([]) do |i, a|
        a << i if divs.any? { |d| i % d == 0 }
      end
      multiples.reduce(0, :+)
    end
  end

  attr_reader :divs

  def initialize(*divisors)
    @divs = divisors.empty? ? self.class.divs : divisors
  end

  def to(n)
    self.class.to(n, divs)
  end
end

# p SumOfMultiples.to(10) #23
# p SumOfMultiples.new(7, 13, 17).to(20) #51

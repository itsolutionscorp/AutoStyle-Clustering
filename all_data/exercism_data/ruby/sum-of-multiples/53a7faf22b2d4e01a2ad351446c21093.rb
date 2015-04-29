class SumOfMultiples
  def initialize(*multiples)
    @multiples = multiples
  end

  def to(number)
    @multiples.map {|m|
      (1..((number - 1) / m)).map { |n| n * m }
    }.flatten.uniq.reduce(0, :+)
  end

  def self.to(number)
    new(3, 5).to(number)
  end
end

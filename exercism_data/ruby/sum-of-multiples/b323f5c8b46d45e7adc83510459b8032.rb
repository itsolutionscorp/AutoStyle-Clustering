class SumOfMultiples
  attr_reader :multiples

  @multiples = [3, 5]

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(number)
    self.class.calc(number, @multiples)
  end

  def self.to(number)
    self.calc(number, @multiples)
  end

  private

  def self.calc(number, multiples)
    multiples.map {|m|
      (1..((number - 1) / m)).map { |n| n * m }
    }.flatten.uniq.reduce(0, :+)
  end
end

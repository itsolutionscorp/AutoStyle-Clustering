class SumOfMultiples

  def initialize(*factors)
    @factors = factors
  end

  def to(limit)
    SumOfMultiples.to(limit, @factors)
  end

  def self.to(limit, factors = [3,5])
    return 0 if limit <= 1

    (1...limit).map do |number|
      factors.any? {|factor| number.modulo(factor).zero? } ? number : 0
    end.inject(:+)
  end

end

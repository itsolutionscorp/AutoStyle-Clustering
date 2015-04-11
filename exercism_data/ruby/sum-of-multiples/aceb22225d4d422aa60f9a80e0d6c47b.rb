class SumOfMultiples
  DEFAULT_MULTIPLICANDS = [3, 5]

  def initialize(*multiplicands)
    @multiplicands = multiplicands.empty? ? DEFAULT_MULTIPLICANDS : multiplicands
  end

  def to(limit)
    (0...limit).reduce(0) { |sum, n| sum + value(n) }
  end

  def self.to(limit)
    self.new.to(limit)
  end

  private

  def value(n)
    summable?(n) ? n : 0
  end

  def summable?(n)
    @multiplicands.any? { |multiple| n.modulo(multiple).zero? }
  end
end

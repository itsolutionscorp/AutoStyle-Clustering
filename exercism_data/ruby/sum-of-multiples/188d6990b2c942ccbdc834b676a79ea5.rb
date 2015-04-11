class SumOfMultiples
  def initialize(*args)
    @factors = *args
  end

  def self.to(up_to, factors = [3,5])
    return 0 if up_to == 1
    (1...up_to).select { |num| self.check_multiple(num, factors) }.reduce(:+)
  end

  def to(up_to)
    self.class.to(up_to, @factors)
  end

  private

  def self.check_multiple(num, factors = [3, 5])
    factors.any? { |el| num % el == 0 }
  end
end

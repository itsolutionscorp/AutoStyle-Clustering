class PrimeFactors
  def self.for(composite)
    new(composite).for
  end

  def initialize(composite)
    @composite = composite
    @factors = []
  end

  def for
    return [] if @composite < 2
    loop do
      factor = smallest_factor
      @factors << factor
      @composite /= factor
      break if @composite == 1
    end
    @factors
  end

  private

  def smallest_factor
    factor = 2
    loop do
      return factor if divisible?(@composite, factor)
      break if factor == @composite
      factor += 1
    end
    fail StandardError, 'No factors found'
  end

  def divisible?(dividend, divisor)
    dividend % divisor == 0
  end
end

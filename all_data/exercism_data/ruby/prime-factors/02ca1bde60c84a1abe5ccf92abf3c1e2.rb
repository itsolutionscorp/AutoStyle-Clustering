class PrimeFactors
  def self.for(n)
    new(n).factors
  end

  attr_reader :factors
  
  def initialize(n)
    @factors = []
    add_factors_for(n)
  end

  private

  def add_factors_for(n)
    first_prime_factor_of(n) do |factor|
      factors << factor
      add_factors_for(n / factor)
    end
  end

  def first_prime_factor_of(n)
    factor = (2..n).find { |factor| n % factor == 0 }
    yield(factor) if factor
  end
end

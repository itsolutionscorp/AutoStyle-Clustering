class SumOfMultiples
  def initialize(*factors)
    @factors = if factors.empty?
                   [3, 5]
                 else
                   factors
                 end
  end

  attr_reader :factors

  def self.to(excluded_max)
    new.to(excluded_max)
  end

  def to(excluded_max)
    sum_over_range(1, excluded_max) do |number|
      multiple?(number) ? number : 0
    end
  end

  private

  def sum_over_range(min, excluded_max)
    (min...excluded_max).reduce(0) do |sum, number|
      sum + yield(number)
    end
  end

  def multiple?(number)
    factors.any? { |factor| number % factor == 0 }
  end
end

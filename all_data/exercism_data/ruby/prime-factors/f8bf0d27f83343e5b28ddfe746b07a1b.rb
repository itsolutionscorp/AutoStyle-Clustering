module Factoring
  refine Fixnum do
    def has_factors?
      self > 1
    end

    def factor_of?(n)
      n.modulo(self).zero?
    end
  end
end

using Factoring

class PrimeFactors

  def self.for(number)
    self.new(number).factors
  end

  def initialize(number)
    @number = number
  end

  def factors
    factors = []
    divisor = 2
    n = @number

    while n.has_factors?
      if divisor.factor_of?(n)
        factors << divisor
        n /= divisor
      else
        divisor = divisor.next
      end
    end
    factors
  end
end

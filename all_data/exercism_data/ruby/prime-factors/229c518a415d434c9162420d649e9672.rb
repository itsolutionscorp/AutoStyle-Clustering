require 'prime'
class PrimeFactors
  class << self
    def for(num)
      Prime.prime_division(num).flat_map { |prime, count| [prime]*count }
    end
  end
end

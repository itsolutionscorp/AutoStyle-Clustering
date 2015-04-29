require "prime"

class PrimeFactors
  def self.for(n)
    Prime.prime_division(n).to_h.inject([]) do |result, (factor, qty)|
      qty.times { result << factor }
      result
    end
  end
end

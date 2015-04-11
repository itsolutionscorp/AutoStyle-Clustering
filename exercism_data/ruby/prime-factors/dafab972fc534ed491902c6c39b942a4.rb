class PrimeFactors
  class << self
    def factors_for(n)
      return [] if n < 2
      divisor = (2..n).find { |factor| n % factor == 0 }
      [divisor] + factors_for(n/divisor)
    end

    alias_method :for, :factors_for
  end
end

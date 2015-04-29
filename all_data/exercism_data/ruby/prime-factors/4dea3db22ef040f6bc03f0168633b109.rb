require 'prime'

class PrimeFactors
  def self.for(n, factors = [])
    return factors if n == 1
    factor = Prime.find { |i| n % i == 0 }
    self.for(n / factor, factors.push(factor))
  end
end

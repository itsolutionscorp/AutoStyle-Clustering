require 'prime'

class PrimeFactors

  def self.for n
    return [] if n == 1
    Prime.each(n).with_object([]) do |prime,factors|
      while n % prime == 0
        factors << prime
        n /= prime
        return factors if n % prime == n
      end
    end
  end

end

require 'prime'

class Prime
  def self.nth(n)
    if n >= 1
      primes = 0
      i = 1
      while primes < n
        i += 1
        primes += 1 if Prime.prime?(i)
      end
      i
    else
      raise ArgumentError.new("Fail")
    end
  end
end

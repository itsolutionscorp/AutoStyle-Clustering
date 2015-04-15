require 'prime'

class Prime

  def self.nth(num)
    if num == 0
      raise ArgumentError
    else
      primes[num-1]
    end
  end

  def self.primes
    Prime.take(10001)
  end
end

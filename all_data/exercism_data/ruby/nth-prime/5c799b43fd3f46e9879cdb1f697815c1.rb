require 'prime'

class Prime

  def self.nth(num)
    raise ArgumentError.new if num == 0
    primes = 0
    result = 1
    while primes < num
      result += 1
      primes += 1 if Prime.prime?(result)
    end
    result
  end
end

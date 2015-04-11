require 'prime'

class Prime
  def self.nth(n)
    if n < 1
      fail ArgumentError
    end

    primes = 0
    i = 0
    loop do
      if Prime.prime?(i)
        primes += 1
        break if primes == n
      end
      i += 1
    end
    i
  end
end

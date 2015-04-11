require 'prime'

class Prime

  def self.nth(n)
    fail ArgumentError.new('There is no such thing. Be reasonable.') if n < 1

    primes = number = 0

    while(primes < n)
      number = number + 1
      primes = primes + 1 if Prime.prime?(number)
    end

    number
  end

end

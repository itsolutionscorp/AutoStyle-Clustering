require 'Prime'

class Prime
  def self.nth(num)
    if num < 1
      message = 'Number is less than 1'
      raise ArgumentError.new(message)
    end

    primes = 0
    i = 1

    while primes < num
      i += 1
      primes += 1 if Prime.prime?(i)
    end
    i
  end
end

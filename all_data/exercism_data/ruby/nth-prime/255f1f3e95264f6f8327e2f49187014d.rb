class Fixnum
  def prime?
    i = 2
    prime = true
    while prime == true && i < 1000 do
      self % i == 0 ? prime = false : nil unless i == self
      i += 1
    end
    prime
  end
end

class Prime
  def self.nth(num)
    raise ArgumentError, "input must be a positive integer" unless num > 0
    primes = []
    i = 2
    while primes.size < num do
      i.prime? ? primes.push(i) : nil
      i += 1
    end
    primes.last
  end
end

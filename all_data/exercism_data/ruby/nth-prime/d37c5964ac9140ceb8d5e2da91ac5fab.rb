class Fixnum
  def prime?
    num = self
    (2..Math.sqrt(num)).each do |i| 
      if num % i == 0 && i < num
        return false
      end
    end 
    true
  end
end

class Prime
  def self.nth(n) 
    raise ArgumentError if n == 0
    primes = [2]
    while primes.length != n
      potential_prime = primes[-1] += 1
      while !potential_prime.prime?
        potential_prime += 1
      end
      primes << potential_prime
    end
    primes[-1]
  end
end

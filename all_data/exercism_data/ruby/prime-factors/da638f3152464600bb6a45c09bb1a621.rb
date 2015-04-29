class PrimeFactors

  def self.for(x)
    possible_factors = self.primes_up_to x 
    divisors = []
    until x <= 1
      f = possible_factors.shift
      while x.modulo(f).zero? 
        x /= f 
        divisors << f
      end
    end
    divisors.sort
  end

  def self.primes_up_to(n)
    possible = n < 2 ? [] : (2..Math.sqrt(n).ceil+1).to_a
    possible.each do |i|
      if i
        j = i**2
        while j < n
          possible[j-2] = nil
          j += i 
        end
      end
    end
    possible.compact
  end
  
end

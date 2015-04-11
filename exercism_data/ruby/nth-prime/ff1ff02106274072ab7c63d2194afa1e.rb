class Prime
  @@primes = [2, 3]
  def self.nth(num)
    raise(ArgumentError) if num < 1
    
    current = @@primes.last
    while @@primes.count < num
      current += 2
      
      is_prime = true
      @@primes.each{ |p|
        if current % p == 0
          is_prime = false
          break
        end
      }
      @@primes << current if is_prime
    end
    
    @@primes[num - 1]
  end
end

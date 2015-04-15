class Prime
  def self.nth(n)
    raise ArgumentError.new "n must be positive" unless n >= 1
    
    primes = []
    curr = 2
    while primes.length < n do
      primes << curr if prime?(curr, primes)
      curr += 1
    end
    primes.last
  end
  
  def self.prime?(number, lower_primes)
    lower_primes.none? { |p| number % p == 0 }
  end
end

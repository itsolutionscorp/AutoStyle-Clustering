class Prime
  def self.nth(n)
    raise ArgumentError if n == 0
    primes = []
    i = 2
    while primes.length < n
      primeCheck = true
      primes.each{|x| primeCheck = false if i % x == 0 }
      primes << i if primeCheck
      i += 1
    end
    primes[n-1]
  end
end

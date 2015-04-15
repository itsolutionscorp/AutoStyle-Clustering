class Prime
  def self.nth(n)
    raise ArgumentError if n < 1
    primes = [2]
    i = 3
    while primes.size < n
      primes.push(i) unless primes.map {|p| i % p}.any? {|k| k == 0}
      i += 2
    end
    
    primes[n - 1]
  end
end

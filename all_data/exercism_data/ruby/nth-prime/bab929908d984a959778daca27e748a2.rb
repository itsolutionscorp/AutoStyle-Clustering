class Prime

  def self.nth(target)
    raise ArgumentError if target <= 0
    begin
      upper_bound = (upper_bound || target) * 2
      primes = sieve_of_eratosthenes(upper_bound)
    end while primes.length < target
    primes[target-1]
  end

private

  # Calculates and memoizes prime numbers between 0 and n
  @@sieve = [false, false, true]
  def self.sieve_of_eratosthenes(n)
    count = @@sieve.length
    @@sieve += [true, false] * ((n-count)/2+1) if n+1 > count
    (3..Math.sqrt(n)).each do |i|
      (3*i..n).step(2*i).each do |j|
        @@sieve[j] = false
      end if @@sieve[i]
    end
    @@sieve.map.with_index{|t, i| i if t && i<=n}.compact
  end

end

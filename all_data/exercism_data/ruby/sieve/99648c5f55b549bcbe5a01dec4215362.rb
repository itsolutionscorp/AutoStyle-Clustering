class Sieve
  def initialize(max)
    @max = max
  end

  def primes
    Sieve.sieve_of_eratosthenes(@max)
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

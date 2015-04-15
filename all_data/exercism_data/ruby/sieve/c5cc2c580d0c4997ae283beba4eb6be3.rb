class Sieve
  attr_reader :primes

  def self.eratosthenes(n)
    primes = [nil, nil, *2..n]
    (2..Math.sqrt(n)).each do |i|
      (i**2..n).step(i) {|mult| primes[mult] = nil} if primes[i]
    end
    primes.compact # remove all nil values
  end

  def initialize n
    @primes = Sieve.eratosthenes n
  end
end

class PrimeFactors

  attr_reader :n
  attr_accessor :primes, :factors

  def initialize(n)
    @n       = n
    @factors = []
    @primes  = sieve_of_eratosthenes
    @factors = all_factors if any_primes?
  end 

  def self.for(n)
    new(n).factors
  end

  private
  def any_primes?
    primes.any?
  end

  def sieve_of_eratosthenes
    array = (2..n).to_a
    array -= array.map { |x| multiples(x, n) }.flatten
  end

  def multiples(x, n)
    2.upto(n/x).map { |i| x*i }
  end

  def unique_prime_factors
    primes.map { |y| y if n%y == 0 }.compact
  end

  def all_factors
    unique_prime_factors.each do |prime|
      limit = n
      while limit%prime == 0 do
        factors << prime 
        limit /= prime
      end
    end
    factors
  end

end

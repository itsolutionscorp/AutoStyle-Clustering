class Sieve

  attr_reader :primes

  def initialize(n)
    @array  = (2..n).to_a
    @primes = sieve_of_eratosthenes(n)
  end

  def sieve_of_eratosthenes(n)
    @array -= @array.map { |x| multiples(x, n) }.flatten
  end

  def multiples(x, n)
    2.upto(n/x).map { |i| x*i }
  end

end

class Sieve
  attr_reader :primes

  def initialize(n)
    @primes = []

    (2..n).each { |i| @primes << i if @primes.all? { |prime| i % prime != 0 } }
  end
end

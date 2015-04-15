class Sieve
  attr_accessor :primes, :numbers

  def initialize(last_number)
    @primes = []
    @numbers = (2..last_number).to_a
  end

  def primes
    until numbers.empty?
      prime = numbers.shift
      @primes << prime
      numbers.reject!{ |num| num % prime == 0 }
    end

    @primes
  end
end

class Sieve
  attr_reader :range
  private :range

  def initialize(limit)
    @range = Range.new(2, limit).to_a
  end

  def primes
    primes = []
    while prime = range.shift
      primes << prime
      range.reject! { |number| number % prime == 0 }
    end
    primes
  end
end

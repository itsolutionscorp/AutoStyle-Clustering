class Sieve
  attr_reader :primes

  def initialize(max)
    # I remove the primes as I find them because it means we don't keep checking
    # the ones we've found over and over
    vals = (2..max).to_a
    @primes = []

    while !vals.empty? do
      @primes << prime = vals.shift
      vals.select! { |x| ( x % prime != 0) }
    end
  end
end

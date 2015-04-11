class Sieve
  attr_reader :range

  def initialize(max)
    @range = (2..max).to_a
  end

  def primes
    results = []
      while range.length > 0
        n = range.shift
        results << n unless results.any?{|prime| n % prime == 0}
      end
    results
  end

end

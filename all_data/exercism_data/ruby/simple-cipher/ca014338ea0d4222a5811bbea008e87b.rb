class Sieve
  attr_reader :range
  private :range

  def initialize(limit)
    @range = Range.new(2, limit).to_a
  end

  def primes
    collect_primes(range)
  end

  private

  def collect_primes(range)
    return [] if range.empty?
    prime = range.first
    [ prime ] + collect_primes(range.reject { |number| number % prime == 0 })
  end
end

class Sieve
  attr_reader :primes

  def initialize(num)
    @range = Array(2..num)
    @primes = []
    find_primes
  end

  def find_primes
    return [] if @range.empty?
    factor = @range.shift
    @range = @range.reject { |num| num % factor == 0 }
    @primes = @primes << factor
    find_primes
  end
end

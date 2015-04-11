class Sieve
  attr_reader :primes

  def initialize target
    @primes = (2..target).to_a
    @max_factor = Math::sqrt(target).ceil
    
    run_sieve_with_factor 2
  end

  private

  def run_sieve_with_factor factor
    return if factor > @max_factor
    @primes -= ((factor * 2)..@primes.last).step(factor).to_a
    run_sieve_with_factor @primes.find { |p| p > factor }
  end
end

# https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
# Very memory inefficient
class PrimeGeneratorEratosthenes
  include Enumerable

  def initialize(upper=2000)
    @upper = upper
    @next_prime = 2
    @marker_list = []
  end

  def self.up_to(upper)
    self.new(upper)
  end

  def each
    while @next_prime != nil and @next_prime <= @upper
      (@next_prime..@upper).lazy.map { |num| num * @next_prime }.each do |mult|
        @marker_list[mult - 1] = true
      end

      yield @next_prime

      @next_prime = ((@next_prime + 1)..@upper).lazy.find { |num|
        @marker_list[num - 1] == nil
      }
    end
  end
end

module Raindrops

  PRIMES = PrimeGeneratorEratosthenes.up_to(2000).to_a

  def self.convert(number)
    factors = prime_factors(number)
    output = ''
    output += 'Pling' if factors.include? 3
    output += 'Plang' if factors.include? 5
    output += 'Plong' if factors.include? 7
    output = number.to_s if output.empty?
    output
  end

  def self.prime_factors(number, factors=[])
    if number == 1 or number == 0
      return []
    end

    smallest_factor = PRIMES.find {|p| number % p == 0}
    factors << smallest_factor
    remaining = number / smallest_factor
    if not PRIMES.include? remaining
      prime_factors(remaining, factors)
    else
      factors << remaining
    end

    factors
  end

end
# vim: ts=2 sw=2

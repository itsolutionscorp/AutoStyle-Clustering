require 'prime'

module Raindrops
  class << self
    DICTIONARY = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    MAX_FACTOR = DICTIONARY.keys.max

    def convert(integer)
      primes = candidate_factors_of(integer)
      translate(primes) || integer.to_s
    end

    private

    def translate(primes)
      words = DICTIONARY.values_at(*primes)
      words.join if words.any?
    end

    def candidate_factors_of(integer)
      factors   = []
      co_factor = integer

      Prime.each do |prime|
        break if 1 == co_factor
        break if prime > MAX_FACTOR

        while 0 == co_factor % prime
          co_factor = co_factor / prime
          factors << prime
        end
      end

      factors.uniq
    end
  end
end

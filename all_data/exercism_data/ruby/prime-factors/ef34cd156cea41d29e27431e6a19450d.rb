require 'mathn'

module PrimeFactors
  def self.for(n)
    Generator.new(n).prime_factors
  end

  class Generator
    attr_reader :n, :primes

    def initialize(n)
      @n      = n
      @primes = Prime.each
    end

    def prime_factors
      [].tap do |factors|
        until n == 1
          until n % factor > 0
            factors << factor
            @n /= factor
          end
          next_factor!
        end
      end
    end

    def factor
      @factor ||= primes.next
    end

    def next_factor!
      @factor = primes.next
    end
  end
end

require 'prime'

class Raindrops
  PRIMES_TO_RAINDROPS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  class << self
    def convert(n)
      raindrops = factorize(n).map do |p|
        PRIMES_TO_RAINDROPS[p]
      end

      raindrops.any? ? raindrops.join : n.to_s
    end

    private

    def factorize(n)
      if n < 1000 * 1000
        division = Prime.prime_division(n) # use default generator
      else
        division = Prime.prime_division(n, Prime::EratosthenesGenerator.new)
      end

      division.map { |p, _e| p }
    end
  end
end

require 'prime'

module MyPrimes
  def self.prime_factors(number)
    factors = []

    while number > 1 do
      primes_until(number).each do |prime|
        if number % prime == 0
          number /= prime
          factors.unshift(prime)
          break
        end
      end
    end

    factors
  end

  def self.primes_until(number)
    primes = []
    Prime.each(number){ |p| primes << p }
    primes.reverse
  end
end

class Raindrops
  include MyPrimes

  def self.convert(number)
    factors = MyPrimes.prime_factors(number)

    raindrops = ""
    raindrops << 'Pling' if factors.include?(3)
    raindrops << 'Plang' if factors.include?(5)
    raindrops << 'Plong' if factors.include?(7)

    if raindrops.empty?
      number.to_s
    else
      raindrops
    end
  end
end

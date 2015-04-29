require 'prime'

class Raindrops
  def self.convert(number)
    primes = factorize(number)
    out    = ""

    out << "Pling" if primes.include?(3)
    out << "Plang" if primes.include?(5)
    out << "Plong" if primes.include?(7)

    out.empty? ? number.to_s : out
  end

  private

  def self.factorize(number, n = 2, primes = [])
    if number >= n
      if number % n == 0
        return factorize(number / n, 2, primes << n)
      end

      return factorize(number, n + 1, primes)
    end

    primes << number if Prime.prime?(number)
    primes
  end
end

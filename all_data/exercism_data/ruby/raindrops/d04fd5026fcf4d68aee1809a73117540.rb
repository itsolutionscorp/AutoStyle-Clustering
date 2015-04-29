require 'prime'

class Raindrops

  def self.primeize(number)
    primes, final_primes = Prime.prime_division(number), []

    primes.each do |p|
      p[1].times.each do
        final_primes << p[0]
      end
    end
    final_primes
  end

  def self.convert(number)
    primes = primeize(number)

    rainspeak = ""
    rainspeak << "Pling" if primes.include?(3)
    rainspeak << "Plang" if primes.include?(5)
    rainspeak << "Plong" if primes.include?(7)

    rainspeak = number.to_s if rainspeak.empty?

    rainspeak
  end

end

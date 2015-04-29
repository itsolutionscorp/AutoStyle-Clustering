require 'prime'

class Raindrops
  def self.convert(number)
    primes = get_primes(number)

    rain_string = ""

    rain_string << 'Pling' if primes.include? 3
    rain_string << 'Plang' if primes.include? 5
    rain_string << 'Plong' if primes.include? 7

    rain_string = number.to_s if rain_string.empty?

    return rain_string
  end

  private
  def self.get_primes(number)
    if Prime.prime? number
      return number
    else
      factors = Prime.prime_division(number)
      return factors.map { |x| x.first }
    end
  end
end

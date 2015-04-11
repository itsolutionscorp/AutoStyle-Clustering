require 'prime'

class Raindrops
  def self.convert(number)
    prime_factors = factorize(number)
    print_string(number, prime_factors)
  end

  private

  SOUNDS = Hash.new('').merge(3 => 'Pling', 5 => 'Plang', 7 => 'Plong')

  def self.print_string(number, prime_factors)
    string = ''

    prime_factors.uniq.each { |n| string << SOUNDS[n] }

    string.empty? ? number.to_s : string
  end

  def self.factorize(number)
    prime_factors = []
    prime_factor = Prime.first

    until number == 1
      prime_factor = find_prime_factor_for(number, prime_factor)

      prime_factors << prime_factor

      number = number / prime_factor
    end

    prime_factors
  end

  def self.find_prime_factor_for(number, prime_factor)
    prime_factor = prime_factor.next until number % prime_factor == 0
    prime_factor
  end
end

require 'prime'

class Raindrops

  def self.convert(number)
    conversion = ''

    factors(number).each do |factor|
      translated_prime = translate_prime(factor)
      translated_prime && conversion << translated_prime
    end

    conversion = number.to_s if conversion.empty?

    conversion

  end

  def self.factors(number)
    Prime.prime_division(number).flatten.sort.uniq
  end

  def self.translate_prime(number)
    if number == 3
      'Pling'

    elsif number == 5
      'Plang'

    elsif number == 7
      'Plong'
    end
  end
end

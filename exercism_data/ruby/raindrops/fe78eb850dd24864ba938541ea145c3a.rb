require 'prime'

class Raindrops

  def self.convert(number)
    conversion = factors(number).map(&method(:translate_prime)).compact!
    conversion && conversion.any? ? conversion.join : number.to_s
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

require 'prime'

class Raindrops
  def self.convert(number)
    output = []

    number_with_prime_factor?(number, 3) { output << "Pling" }
    number_with_prime_factor?(number, 5) { output << "Plang" }
    number_with_prime_factor?(number, 7) { output << "Plong" }

    return output.join unless output.empty?
    number.to_s
  end

  def self.number_with_prime_factor?(number, factor, &block)
    yield if Prime.prime_division(number).flatten.include?(factor)
  end
end

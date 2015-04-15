require 'prime'

class Raindrops
  def self.convert(number)
    converted_string = ""
    factors = number.prime_division
    factors.map! { |factor| factor.first }
    converted_string += "Pling" if factors.include?(3)
    converted_string += "Plang" if factors.include?(5)
    converted_string += "Plong" if factors.include?(7)
    converted_string.empty? ? number.to_s : converted_string
  end
end

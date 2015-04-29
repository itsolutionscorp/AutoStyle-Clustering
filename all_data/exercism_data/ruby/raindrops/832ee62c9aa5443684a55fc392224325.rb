require 'Prime'
require 'byebug'

class Raindrops
  def self.convert(number)
    return_string = ""
    prime_factors = Prime.prime_division(number).flat_map { |factor, power| [factor] * power }

    if prime_factors.include? 3 then return_string += "Pling" end
    if prime_factors.include? 5 then return_string += "Plang" end
    if prime_factors.include? 7 then return_string += "Plong" end

    return_string.empty? ? number.to_s : return_string
  end
end

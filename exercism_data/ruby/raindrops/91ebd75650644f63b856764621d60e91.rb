require 'prime'

class Raindrops

  def self.convert(number)
    prime_factors = number.prime_division.flatten

    result = ""

    result += 'Pling' if prime_factors.include?(3)
    result += 'Plang' if prime_factors.include?(5)
    result += 'Plong' if prime_factors.include?(7)

    result.empty? ? number.to_s : result
  end

end

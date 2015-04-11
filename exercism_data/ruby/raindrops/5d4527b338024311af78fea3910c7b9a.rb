require 'prime'

class Raindrops

  def self.convert(number)
    factors = number.prime_division.map { |n, exp| n }
    output = ""
    output += "Pling" if factors.include?(3)
    output += "Plang" if factors.include?(5)
    output += "Plong" if factors.include?(7)
    (output.empty?) ? number.to_s : output
  end

end

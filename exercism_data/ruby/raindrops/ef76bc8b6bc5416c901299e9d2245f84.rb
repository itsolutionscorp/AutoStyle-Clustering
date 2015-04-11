require 'prime'

class Raindrops

  def self.convert(number)
    factors = number.prime_division.flatten.sort
    output  = ''

    output += 'Pling' if factors.include? 3
    output += 'Plang' if factors.include? 5
    output += 'Plong' if factors.include? 7

    output = number.to_s if output.empty?
    output
  end
end

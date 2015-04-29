require 'prime'

class Raindrops
  def self.convert(number)
    output = ''
    divisors = number.prime_division.flatten
    divisors.each do |num|
      output += 'Pling' if num == 3
      output += 'Plang' if num == 5
      output += 'Plong' if num == 7
    end
    output == '' ? number.to_s : output
  end
end

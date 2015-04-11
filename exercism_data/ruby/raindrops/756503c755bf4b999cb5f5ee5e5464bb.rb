require 'prime'

class Raindrops

  def self.convert(number)
    factors = Prime.prime_division(number).flat_map { |factor, power| [factor] * power }
    values = [3, 5, 7]
    output = ""

    output += 'Pling' if factors.include? 3
    output += 'Plang' if factors.include? 5
    output += 'Plong' if factors.include? 7
    output = number.to_s if !(factors & values).any?
    output
  end

end

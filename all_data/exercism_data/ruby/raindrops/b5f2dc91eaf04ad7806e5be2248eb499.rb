require 'prime'

class Raindrops
  def self.convert(num)
    string = ''

    factors = Prime.prime_division(num).flat_map {
      |factor, power| [factor]
    }

    string += 'Pling' if factors.include? 3
    string += 'Plang' if factors.include? 5
    string += 'Plong' if factors.include? 7

    return string unless string.empty?
    num.to_s
  end
end

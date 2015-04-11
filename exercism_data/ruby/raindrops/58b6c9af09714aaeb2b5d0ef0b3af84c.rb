require 'prime'
class Raindrops
  def self.convert(number)
    string = ''
    p_numbers = Prime.prime_division(number).flatten.uniq
    string += 'Pling' if p_numbers.include?(3)
    string += 'Plang' if p_numbers.include?(5)
    string += 'Plong' if p_numbers.include?(7)
    return string.empty? ? number.to_s : string
  end
end

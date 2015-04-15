require 'prime'

class Raindrops
  def self.convert(num)
    prime_factors = num.prime_division.map {|arr| arr.first}
    string = ''

    string += 'Pling' if prime_factors.include?(3)
    string += 'Plang' if prime_factors.include?(5)
    string += 'Plong' if prime_factors.include?(7)
    string == '' ? (return num.to_s) : (return string)
  end
end

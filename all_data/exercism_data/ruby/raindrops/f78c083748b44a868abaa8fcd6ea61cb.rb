require 'prime'

class Raindrops

  def self.convert(num)
    array = []
    array << 'Pling' if Prime.prime_division(num).flatten.include?(3)
    array << 'Plang' if Prime.prime_division(num).flatten.include?(5)
    array << 'Plong' if Prime.prime_division(num).flatten.include?(7)
    array << num.to_s if array.empty?
    array.join
  end
end

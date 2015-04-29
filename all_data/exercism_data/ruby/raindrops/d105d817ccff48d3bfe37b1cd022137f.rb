#require 'math'
require 'prime'

class Raindrops
  def self.convert(num)
    result = ''
    factors = num.prime_division.map { |arr| arr.first }
    result += 'Pling' if factors.include?(3)
    result += 'Plang' if factors.include?(5)
    result += 'Plong' if factors.include?(7)

    return result if result != ''
    return num.to_s
  end
end

require 'prime'

class Raindrops
  def self.convert(num)
    factors = Prime.prime_division(num).map { |n| n[0] }
    result = ''
    result << 'Pling' if factors.include?(3)
    result << 'Plang' if factors.include?(5)
    result << 'Plong' if factors.include?(7)
    result.empty? ? num.to_s : result
  end
end

require 'prime'
class Raindrops

  def self.convert(digit)
    return '1' if digit == 1
    factors = digit.prime_division.flatten
    return_me = ''
    return_me += 'Pling' if factors.include?(3)
    return_me += 'Plang' if factors.include?(5)
    return_me += 'Plong' if factors.include?(7)
    return_me = digit.to_s unless (factors & [3,5,7]).any?
    return_me
  end
end

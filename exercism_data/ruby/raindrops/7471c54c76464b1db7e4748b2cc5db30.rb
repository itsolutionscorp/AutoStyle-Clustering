require 'prime'

class Raindrops

  def self.convert(int)
    str = ""
    str << "Pling" if self.factor(int).include?(3)
    str << "Plang" if self.factor(int).include?(5)
    str << "Plong" if self.factor(int).include?(7)
    str << int.to_s unless [3, 5, 7].any? { |x| self.factor(int).include?(x) }
    str
  end

private
  def self.factor(int)
    Prime.prime_division(int).flatten
  end
end

require 'prime'

class Raindrops

  def self.convert(int)
    str = ""
    str << "Pling" if self.factor(int).include?(3)
    str << "Plang" if self.factor(int).include?(5)
    str << "Plong" if self.factor(int).include?(7)
    str.empty? ? int.to_s : str
  end

private
  def self.factor(int)
    Prime.prime_division(int).flatten
  end
end

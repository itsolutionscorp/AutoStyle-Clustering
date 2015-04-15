require 'prime'

class Raindrops

  def self.convert(num)
    output = []
    output << 'Pling' if divisible_by(num, 3)
    output << 'Plang' if divisible_by(num, 5)
    output << 'Plong' if divisible_by(num, 7)
    output << num.to_s if divisible_by_none(num)
    output.join("")
  end

  def self.divisible_by num, divisor
    num % divisor == 0
  end

  def self.divisible_by_none num
    !(divisible_by(num, 3) || divisible_by(num, 5) || divisible_by(num, 7))
  end

end

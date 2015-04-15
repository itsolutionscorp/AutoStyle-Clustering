class Fixnum
  def divisible_by?(divisor)
    self % divisor == 0
  end
end

class Raindrops
  def self.convert(number)
    output = []

    output << "Pling" if number.divisible_by? 3
    output << "Plang" if number.divisible_by? 5
    output << "Plong" if number.divisible_by? 7

    output.empty? ? number.to_s : output.join
  end
end

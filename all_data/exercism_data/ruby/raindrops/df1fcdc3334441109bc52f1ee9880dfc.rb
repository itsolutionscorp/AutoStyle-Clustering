class Raindrops

  def self.convert(num)
    result = ""
    result << "Pling" if num.divisible_by?(3)
    result << "Plang" if num.divisible_by?(5)
    result << "Plong" if num.divisible_by?(7)

    unless result.empty?
      result
    else
      num.to_s
    end
  end

end

class Fixnum

  def divisible_by?(divisor)
    self % divisor == 0
  end

end

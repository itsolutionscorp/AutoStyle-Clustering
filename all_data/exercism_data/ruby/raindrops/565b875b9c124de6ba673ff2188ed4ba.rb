class Raindrops

  def self.convert(num)
    return num.to_s if not_divisible? num

    result = ""
    result << "Pling" if divisble_by_three? num
    result << "Plang" if divisble_by_five? num
    result << "Plong" if divisble_by_seven? num
    result
  end

  private

  def self.not_divisible? num
    num % 3 != 0 && num % 5 != 0 && num % 7 != 0
  end

  def self.divisble_by_three? num
    num % 3 == 0
  end

  def self.divisble_by_five? num
    num % 5 == 0
  end

  def self.divisble_by_seven? num
    num % 7 == 0
  end

end

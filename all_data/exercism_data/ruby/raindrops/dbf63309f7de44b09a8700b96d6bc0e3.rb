class Raindrops

  def self.convert(num)
    result = ""
    result << "Pling" if divisble_by_three? num
    result << "Plang" if divisble_by_five? num
    result << "Plong" if divisble_by_seven? num

    return result.empty? ? num.to_s : result
  end

  private

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

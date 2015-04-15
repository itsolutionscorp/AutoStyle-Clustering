class Raindrops
  def self.convert(number)
    result = []
    if is_divisible_by?(3, number)
      result << 'Pling'
    end
    if is_divisible_by?(5, number)
      result << 'Plang'
    end
    if is_divisible_by?(7, number)
      result << 'Plong'
    end
    result != [] ? result.join : number.to_s
  end

  def self.is_divisible_by?(divisible_by, number)
    number % divisible_by == 0
  end
end

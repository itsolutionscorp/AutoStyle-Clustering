class Raindrops
  def self.convert(num)
    return num.to_s if num % 3 != 0 && num % 5 != 0 && num % 7 != 0
    result = ''
    result << "Pling" if num % 3 == 0
    result << "Plang" if num % 5 == 0
    result << "Plong" if num % 7 == 0
    return result
  end
end

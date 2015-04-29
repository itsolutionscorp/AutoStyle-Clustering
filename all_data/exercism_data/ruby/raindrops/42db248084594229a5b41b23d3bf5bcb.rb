class Raindrops
  def self.convert(num)
    result = ""
    factors = factors_for(num)
    result += "Pling" if factors.include? 3 
    result += "Plang" if factors.include? 5
    result += "Plong" if factors.include? 7
    result.empty? ? num.to_s : result
  end

  def self.factors_for(num)
    (1..num).reject { |n| num % n != 0 }
  end
end

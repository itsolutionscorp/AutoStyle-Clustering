class Raindrops
  def self.convert(num)
  	result = ""
  	factors = self.divisors_of(num)
    factors.each do |factor|
      if factor == 3
      	result += "Pling"
      elsif factor == 5
      	result += "Plang"
      elsif factor == 7
      	result += "Plong"
      end
    end
    if result == "" then num.to_s else result end
  end

  def self.divisors_of(num)
    (1..num).select { |n|num % n == 0 }
  end
end

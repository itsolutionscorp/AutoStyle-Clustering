class Raindrops
  def self.convert(num)
    result = ""
    result << "Pling" if num % 3 == 0
    result << "Plang" if num % 5 == 0
    result << "Plong" if num % 7 == 0
    result.size > 0 ? result : num.to_s
  end
end

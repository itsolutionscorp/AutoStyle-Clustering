class Raindrops
  def self.convert(num)
    values = (1..num).select { |x| num % x == 0 }
    output = []
    output << "Pling" if values.include?(3)
    output << "Plang" if values.include?(5)
    output << "Plong" if values.include?(7)
    output << num.to_s if output[0] == nil
    output.join("")
  end
end

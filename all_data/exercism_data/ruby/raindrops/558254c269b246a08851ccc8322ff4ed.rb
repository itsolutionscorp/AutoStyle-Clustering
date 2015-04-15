class Raindrops
  Table = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(num)
    result = [3, 5, 7].map { |factor| drop(num, factor) }.reduce(:+)
    if result.empty? then num.to_s else result end
  end

  def self.drop(num, factor)
    if num % factor == 0
      Table[factor]
    else
      ""
    end
  end

end

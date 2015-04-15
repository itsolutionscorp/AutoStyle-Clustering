class Raindrops
  def self.convert(a)
    output = []
    if a%3 == 0
      output << "Pling"
    end
    if a%5 == 0
      output << "Plang"
    end
    if a%7 == 0
      output << "Plong"
    end
    output.empty? ? a.to_s : output.join("")
  end
end

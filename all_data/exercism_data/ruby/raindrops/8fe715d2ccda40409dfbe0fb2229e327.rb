class Raindrops

  def self.convert(num)
    output = ""
    if num % 3 == 0
      output << "Pling"
    end
    if num % 5 == 0
      output << "Plang"
    end
    if num % 7 == 0
      output << "Plong"
    end
    if output.empty?
      output << num.to_s
    end
    output
  end
end

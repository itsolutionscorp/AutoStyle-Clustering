class Raindrops
  def self.convert(input_i)
    output = ""
    if input_i % 3 == 0
      output << "Pling"
    end
    if input_i % 5 == 0
      output << "Plang"
    end
    if input_i % 7 == 0
      output << "Plong"
    end
    if output.eql? ""
      output << input_i.to_s
    end
    output
  end
end

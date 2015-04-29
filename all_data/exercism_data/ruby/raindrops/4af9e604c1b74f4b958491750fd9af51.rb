class Raindrops
  def self.convert(input)
    output = ""

    if input % 3 == 0
      output << "Pling"
    end

    if input % 5 == 0
      output << "Plang"
    end

    if input % 7 == 0
      output << "Plong"
    end

    output.empty? ? input.to_s : output
  end
end

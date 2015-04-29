class Raindrops
  def self.convert(number)
    output = String.new

    if number.modulo(3).zero?
      output << "Pling"
    end

    if number.modulo(5).zero?
      output << "Plang"
    end

    if number.modulo(7).zero?
      output << "Plong"
    end

    if output.empty?
      output << number.to_s
    end

    output
  end
end

Raindrops = Struct.new(:number) do
  def self.convert(number)
    new(number).convert
  end

  def convert
    output = ""
    output << "Pling" if number.modulo(3).zero?
    output << "Plang" if number.modulo(5).zero?
    output << "Plong" if number.modulo(7).zero?
    output << number.to_s if output.empty?
    output
  end
end

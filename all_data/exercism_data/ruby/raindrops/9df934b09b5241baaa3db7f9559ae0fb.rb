class Raindrops
  def self.convert(number)
    output = String.new

    output << "Pling" if is_factor?(number, 3)
    output << "Plang" if is_factor?(number, 5)
    output << "Plong" if is_factor?(number, 7)
    output << number.to_s if is_blank?(output)

    output
  end

  private

  def self.is_factor?(number, factor)
    number % factor == 0
  end

  def self.is_blank?(output)
    output == ""
  end
end

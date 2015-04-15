class Octal
  attr_reader :octal
  
  def initialize(octal)
    @octal = octal
  end

  def to_decimal
   valid? || octal.to_i(8)
  end

  def valid?
    0 if octal.chars.any? { |number| number > "8" }
  end
end

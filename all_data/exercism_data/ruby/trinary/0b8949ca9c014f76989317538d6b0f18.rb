# Converts trinary numbers into their decimal value.
class Trinary
  attr_reader :trinary

  def initialize(string)
    @trinary = string.split('').reverse.map(&:to_i)
  end

  def to_decimal
    decimal = 0
    trinary.each_with_index do |num, index|
      decimal += num * 3**index
    end
    decimal
  end
end

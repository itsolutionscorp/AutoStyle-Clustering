class Trinary
  def initialize(trinary_number)
    @trinary_number = trinary_number
  end

  def to_decimal
    trinary_string = @trinary_number.to_s
    decimal = 0
    trinary_array = trinary_string.chars
    trinary_array.reverse.each_with_index do |digit, index|
      decimal += digit.to_i * (3 ** index)
    end
    return decimal
  end
end

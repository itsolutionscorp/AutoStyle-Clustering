class Trinary
  def initialize(trinary_string)
    @trinary_string = trinary_string
  end

  def to_decimal
    sum = 0
    return sum if /[^0-2]/.match(@trinary_string)
    @trinary_string.chars.reverse.each_with_index do |digit, index|
      sum += digit.to_i * (3**index)
    end
    sum
  end
end

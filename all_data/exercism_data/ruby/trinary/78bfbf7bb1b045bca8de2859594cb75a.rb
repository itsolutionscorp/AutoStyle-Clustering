class Trinary
  def initialize(digits)
    @digits = digits.split('')
  end

  def to_decimal
    decimal = 0
    @digits.reverse.each_with_index do |digit, index|
      decimal += digit.to_i * scalar_at(index)
    end
    decimal
  end

  private
  def scalar_at(index)
    3**index
  end
end

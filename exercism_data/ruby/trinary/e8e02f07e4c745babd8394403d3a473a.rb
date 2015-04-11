class Trinary
  class InvalidDigitError < StandardError; end;

  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    begin
      digits.each_with_index.reduce(0) do |sum, (digit, index)|
        sum + digit_decimal_value(digit, index)
      end
    rescue InvalidDigitError
      0
    end
  end

  private

  def digits
    @trinary.chars.reverse
  end

  def trinary_digit?(digit)
    digit == "0" || digit == "1" || digit == "2"
  end

  def digit_decimal_value(digit, index)
    raise InvalidDigitError unless trinary_digit?(digit)
    digit.to_i * 3 ** index
  end
end

class Binary
  class InvalidDigitError < StandardError; end;

  def initialize(binary)
    @binary = binary
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
    @binary.chars.reverse
  end

  def binary_digit?(digit)
    digit == "0" || digit == "1"
  end

  def digit_decimal_value(digit, index)
    raise InvalidDigitError unless binary_digit?(digit)
    digit == "1" ? 2 ** index : 0
  end
end

class Binary
  def initialize(digits)
    @digits = normalize(digits)
  end

  def to_decimal
    @digits.reverse.chars.each_with_index.inject(0) do |decimal, (binary_digit, position)|
      decimal + binary_digit.to_i * 2**position
    end
  end

  private

  def normalize(digits)
    digits.match(/[^01]/) ? "0" : digits
  end
end

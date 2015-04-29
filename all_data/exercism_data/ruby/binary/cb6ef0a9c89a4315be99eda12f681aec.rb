class Binary
  def initialize(big_endian)
    @big_endian = normalize(big_endian)
  end

  def to_decimal
    to_little_endian.chars.each_with_index.inject(0) do |decimal, (binary_digit, position)|
      decimal + binary_digit.to_i * 2**position
    end
  end

  private

  def normalize(string)
    string.match(/[^01]/) ? "0" : string
  end

  def to_little_endian
    @big_endian.reverse
  end
end

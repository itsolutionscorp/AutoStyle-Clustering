class Hexadecimal
  def initialize(hex_string)
    @hex_string = if valid?(hex_string)
      hex_string
    else
      "0"
    end
  end

  def to_decimal
    @decimal ||= convert_to_decimal
  end

  private
  def valid?(hex_string)
    hex_string.downcase.match(/\A[0-9a-f]+\z/)
  end

  def convert_to_decimal
    i = 0
    digits.reduce(0) do |value, digit|
      value += digit * 16**i
      i += 1
      value
    end
  end

  def digits
    @digits ||= @hex_string.chars.reverse.map do |hex_char|
      if hex_char.match(/\d/)
        hex_char.to_i
      else
        10 + hex_char.ord - "a".ord
      end
    end
  end
end

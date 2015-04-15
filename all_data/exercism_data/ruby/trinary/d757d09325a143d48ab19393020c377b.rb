class Trinary
  attr_accessor :string_value

  BASE_VALUE = 3

  def initialize(string_value)
    @string_value = string_value
  end

  def to_decimal
    digits_with_exponents.map do |digit, exponent|
      digit * (BASE_VALUE ** exponent)
    end.reduce(&:+)
  end

  def digits_with_exponents
    string_value.chars.to_a.reverse.each_with_index.map do |digit, exponent|
      [digit.to_i, exponent]
    end
  end
end

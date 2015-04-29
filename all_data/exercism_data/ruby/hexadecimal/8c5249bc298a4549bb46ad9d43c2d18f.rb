class Hexadecimal
  def initialize(hex_string)
    @hex_string = hex_string
    @decimal = 0 unless hex_string.match(/^[0-9A-F]+$/i)
  end

  def to_decimal
    return @decimal unless @decimal.nil?
    converted_digits = @hex_string.each_char.map(&@@convert_digit)
    @decimal = 0
    converted_digits.reverse.each_with_index do |digit, index|
      @decimal += (16**index) * digit
    end
    @decimal
  end

  @@convert_digit = ->(digit_char) {
    case digit_char.upcase
    when /[ABCDEF]/ then 10 + 'ABCDEF'.index(digit_char.upcase)
    else digit_char.to_i
    end
  }
end

class Hexadecimal

  def initialize(hex_string)
    @hex_digits = hex_string.split('')
  end

  def convert_hex
    @hex_digits.map do |digit|
      case digit
        when 'a'
          10
        when 'b'
          11
        when 'c'
          12
        when 'd'
          13
        when 'e'
          14
        when 'f'
          15
        else digit.to_i
      end
    end
  end

  def to_decimal
    return 0 if @hex_digits.join('').match(/[g-z]+/)
    multipliers = (0..@hex_digits.length-1).to_a.reverse
    convert_hex.zip(multipliers).map {|a,b| a * (16 ** b)}.inject(:+)
  end

end

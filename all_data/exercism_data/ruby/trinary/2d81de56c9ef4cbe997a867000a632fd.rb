class Trinary
  NUMBER_BASE = 3
  def initialize(string)
    @string = string
  end

  def to_decimal
    string_to_convert = @string.reverse.split('')
    result = 0
    string_to_convert.each_with_index { |value, index | result += value.to_i * NUMBER_BASE**index }
    result
  end
end

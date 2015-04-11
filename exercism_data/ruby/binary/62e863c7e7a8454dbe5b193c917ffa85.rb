class Binary
  def initialize(binary_string)
    @binary_string = binary_string
  end

  def to_decimal
    return 0 unless @binary_string =~ /^[01]+$/
    digits = @binary_string.reverse.split('')
    digits.map.with_index do |digit, i|
      digit.to_i * 2**i
    end.inject(:+)
  end
end

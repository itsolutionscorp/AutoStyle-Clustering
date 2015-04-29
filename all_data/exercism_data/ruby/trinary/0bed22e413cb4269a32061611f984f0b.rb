class Trinary
  def initialize(trinary_string)
    @trinary_string = trinary_string
  end

  def to_decimal
    return 0 unless @trinary_string =~ /^[012]+$/
    digits = @trinary_string.reverse.split('')
    digits.map.with_index do |digit, i|
      digit.to_i * 3**i
    end.inject(:+)
  end
end

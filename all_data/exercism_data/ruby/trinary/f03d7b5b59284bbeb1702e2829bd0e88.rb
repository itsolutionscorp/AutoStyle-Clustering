class Trinary
  attr_reader :trinary_string
  def initialize(trinary_string)
    @trinary_string = trinary_string
  end

  def to_decimal
    trinary_string.reverse.chars.map.with_index do |char, index|
      char.to_i * 3 ** index
    end.inject(:+)
  end
end

class Trinary

  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    @trinary.each_char.reduce(0) { |sum, d| sum * 3 + d.to_i }
  end

end

class Trinary
  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    tri_a = @trinary.chars.to_a.reverse
    (0..tri_a.length).inject(0) { |sum, i| sum + tri_a[i].to_i * 3**(i) }
  end
end

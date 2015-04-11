# Converts a ternary string to decimal
# using first principles
class Trinary
  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    valid? ? convert : 0
  end

  def convert
    @trinary.reverse.chars
      .each_with_index
      .inject(0) { |ans, (c, i)| ans + c.to_i * 3 ** i }
  end

  def valid?
    @trinary.match(/\A[0-2]+\z/)
  end
end

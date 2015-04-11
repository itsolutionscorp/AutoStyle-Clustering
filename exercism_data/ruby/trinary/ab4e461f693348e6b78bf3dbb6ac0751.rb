class Trinary
  def initialize input
    @trinary_num = validate input
  end

  def to_decimal
    @trinary_num.chars.reduce(0) { |sum, trinary| sum * 3 + trinary.to_i}
  end

  private
  def validate input
    /^[0-2]*$/ === input ? input : '0'
  end
end

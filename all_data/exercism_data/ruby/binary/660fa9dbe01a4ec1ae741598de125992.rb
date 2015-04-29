class Binary
  def initialize input
    @binary_num = validate input
  end

  def to_decimal
    @binary_num.chars.reduce(0) { |sum, binary| sum * 2 + binary.to_i }
  end

  private
  def validate input
    /^[0-1]*$/ === input ? input : '0'
  end
end

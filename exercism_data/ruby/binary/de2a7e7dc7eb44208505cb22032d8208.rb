class Binary
  def initialize num
    @binary_num = num
  end

  def to_decimal
    if /^[0-1]*$/ === @binary_num
      @binary_num.chars.reduce(0) { |sum, binary| sum * 2 + binary.to_i }
    else
      0
    end
  end
end

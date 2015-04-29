class Binary
  def initialize(decimal_string)
    @decimal_string = decimal_string
  end

  def to_decimal
    reverse_dec_chars = @decimal_string.chars.reverse
    if is_binary_string?
      reverse_dec_chars.each_with_index.map { |n, index| n.to_i * 2**(index) }.reduce(&:+)
    else
      0
    end
  end
  def is_binary_string?
    @decimal_string =~ /^[01]+$/
  end
end

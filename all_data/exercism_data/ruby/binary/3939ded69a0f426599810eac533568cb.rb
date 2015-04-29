class Binary
  attr_reader :str
  def initialize(binary_string)
    @str = binary_string
  end

  def to_decimal
    return 0 if str_not_binary
    binary_array.each_with_index.inject(0) { |decimal, (val, index)| decimal + (val.eql?(1) ? 2 ** index : 0) }
  end

  private
  def str_not_binary
    str =~ /[^01]+/
  end

  def binary_array
    str.reverse.chars.map(&:to_i)
  end
end

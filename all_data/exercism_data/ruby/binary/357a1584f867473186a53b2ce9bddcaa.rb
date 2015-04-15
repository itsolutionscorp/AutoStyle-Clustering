class Binary
  attr_reader :str
  def initialize(binary_string)
    @str = binary_string
  end

  def to_decimal
    return 0 if str_not_binary
    binary_array.each_with_index.map do |s, i|
      s.eql?(1) ? 2 ** i : 0
    end.inject(:+)
  end

  private
  def str_not_binary
    str =~ /[^01]+/
  end

  def binary_array
    str.reverse.chars.map { |c| c.to_i }
  end
end

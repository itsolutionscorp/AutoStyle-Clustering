class Binary
  def initialize(num)
    # reversing the number will make it easier to calculate the exponent
    @num = valid_binary?(num) ? num.reverse : '0'
  end

  def to_decimal
    # return 0 if
    @num.each_char.with_index.reduce(0) { | sum, (value, index) | sum + value.to_i * 2 ** index }
  end

  def valid_binary?(num)
    num =~ /^[01]+$/
  end
end

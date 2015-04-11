class Binary
  def initialize(number_string)
    @number_string = number_string
  end

  def to_decimal
    base_10 = 0
    unless @number_string[/\D/]
      @number_string.split("").reverse.each_with_index {|digit, index| base_10 += digit.to_i * 2 ** index}
    end
    base_10
  end
end

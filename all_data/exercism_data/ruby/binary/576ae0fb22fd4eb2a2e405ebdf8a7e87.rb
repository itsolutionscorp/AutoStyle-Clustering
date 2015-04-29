class Binary

  def initialize(num)
    @num = num
  end

  def to_decimal
    if @num =~ /[^0-9]/
      return 0
    end
    binary_array = @num.chars.to_a
    binary_array.reverse!
    sum = 0
    decimal_value = 1
    binary_array.each do |b_digit|
      if b_digit.to_i == 1
        sum += decimal_value
      end
      decimal_value *= 2
    end
    sum
  end
end

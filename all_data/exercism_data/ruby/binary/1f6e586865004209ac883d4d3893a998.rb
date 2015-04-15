class Binary
  def initialize (binary_number)
    @binary_number = binary_number
  end

  def to_decimal
    decimal_sum = 0
    counter = 0
    if @binary_number =~ /[a-z]/
      decimal_sum = 0
    else
      @binary_number= @binary_number.chars
      @binary_number.map! { |x| x.to_i }
      @binary_number.reverse.each do |x|
        decimal_sum += x * 2 ** (counter)
        counter += 1
      end
    end
    decimal_sum
  end

end

class Binary

  def initialize(num)
    @places = num.size
    @num = num
  end

  def to_decimal
    return 0 if @num.to_i == 0
    @num.split("").each_with_index.inject(0) do |result, (digit, i)|
      result += digit.to_i * 2**(@places - i-1)
      result
    end
  end

end

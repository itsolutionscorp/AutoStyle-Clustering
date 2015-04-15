class Binary

  def initialize(num)
    @num = num
  end

  def to_decimal
    return 0 unless @num =~ /^[01]+$/
    @num.split('').reverse.each_with_index.inject(0) do |memo, (digit,index)|
      if digit == '1' then
        memo + 2**index
      else
        memo
      end
    end
  end

end

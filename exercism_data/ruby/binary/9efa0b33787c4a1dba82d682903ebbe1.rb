class Binary
  def initialize(number)
    @number = number
  end

  def to_decimal
    return 0 unless @number =~ /\A(0|1)+\z/
    @number.reverse.each_char.with_index.reduce(0) do |sum, (char, pow)|
      sum += (char.to_i * (2**pow)) if char
    end
  end
end

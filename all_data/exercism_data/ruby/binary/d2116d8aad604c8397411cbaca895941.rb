class Binary
  def initialize(number)
    @number = number =~ /\A(0|1)+\z/ ? number.reverse.chars : [0]
  end

  def to_decimal
    @number.each_with_index.reduce(0) do |sum, (char, pow)|
      sum += (char.to_i * (2**pow)) if char
    end
  end
end

class Binary
  attr_reader :number

  def initialize(number)
    @number = number.to_i.to_s
  end

  def to_decimal
    number.chars.reverse.each_with_index.inject(0) do |sum, (value, index)|
      sum + value.to_i * (2 ** index)
    end
  end
end

class Binary
  def initialize input
    @input = input.each_char.to_a.reverse.map(&:to_i)
  end

  def to_decimal
    return 0 if !input_1s_and_0s
    input.each_with_index.inject 0 do |sum, (digit, index)|
      sum += (digit * 2 ** index)
    end
  end

  private
  def input_1s_and_0s
    input.all? { |digit| digit == 1 || digit == 0 }
  end

  attr_reader :input
end

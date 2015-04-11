class Binary
  def initialize input
    @input = normalize(input).chars.reverse.map(&:to_i)
  end

  def to_decimal
    input.each_with_index.inject 0 do |sum, (digit, index)|
      sum += (digit * 2 ** index)
    end
  end

  private
  def normalize input
    input.match(/[^10]/) ? '0' : input
  end

  attr_reader :input
end

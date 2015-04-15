class Trinary
  def initialize(number)
    @number = number
  end

  def to_decimal
    return 0 unless valid?
    @number.chars.reverse.each_with_index.reduce(0) do |sum, (digit, index)|
      sum + digit.to_i * (3 ** index)
    end
  end

private

  def valid?
    @number[/^[012]+$/]
  end

end

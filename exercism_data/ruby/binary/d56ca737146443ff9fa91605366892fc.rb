class Binary

  attr_reader :binary

  def initialize binary
    @binary = binary
  end

  def to_decimal
    digits.each_with_index.reduce(0) do |result, (digit, index)|
      result += digit * (2 ** index)
    end
  end

private

  def digits
    return [0] unless binary =~ /^[01]+$/

    binary.chars.reverse.map &:to_i
  end

end

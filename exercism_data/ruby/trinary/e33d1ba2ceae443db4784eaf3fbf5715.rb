class Trinary
  attr_reader :digits

  def initialize(string)
    @digits = string.chars.map(&:to_i)
  end

  def to_decimal
    return 0 unless valid?

    digits.reverse.each_with_index.inject(0) do |result, (digit, index)|
      result += digit * (3 ** index)
    end
  end

  def valid?
    (digits - [0, 1, 2]).empty?
  end
end

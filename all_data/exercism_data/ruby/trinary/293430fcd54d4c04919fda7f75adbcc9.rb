class Trinary
  def initialize(number)
    @number = number
  end

  def to_decimal
    return 0 if @number.gsub(/[^0-2]/, "") != @number

    @number.chars
           .reverse
           .map(&:to_i)
           .each_with_index
           .reduce(0) { |result, (digit, index)| result += digit * (3 ** index) }
  end
end

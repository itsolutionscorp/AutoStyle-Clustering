class Trinary
  def initialize(string)
    @ints = string.each_char.map(&:to_i).reverse
  end
  def to_decimal
    sum = 0
    @ints.each_with_index do |int, index|
      sum += int * (3 ** index)
    end
    sum
  end
end

class Trinary
  def initialize(digit_string)
    @digit_string = digit_string
  end

  def to_decimal
    digit_array = @digit_string.split('').reverse
    digit_array.each_with_index.reduce(0) do |sum, (number_string, index)|
      sum + number_string.to_i * 3 ** index
    end
  end
end

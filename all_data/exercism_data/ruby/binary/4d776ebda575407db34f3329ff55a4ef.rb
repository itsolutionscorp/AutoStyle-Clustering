class Binary
  def initialize(binary_num)

    # As long as input is a valid binary number, save each digit in 
    # an array (in reverse order so that the array index matches the
    # digit's place value.   2^0, 2^1, etc...) 
    if binary_num.to_s.gsub(/[^01]/, "") == binary_num.to_s
      @digits = binary_num.to_s.reverse.split('').map { |d| d.to_i }
    else
      @digits = []
    end
  end


  def to_decimal

    # For each array index, if that position contains a one, 
    # add 2**index to sum and return sum at the end
    sum = 0
    (0...@digits.length).each do |i|
      if @digits[i] == 1
        sum += 2**i
      end
    end
    sum
  end
end

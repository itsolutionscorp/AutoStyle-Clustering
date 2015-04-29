class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    # prepare string for reading left to right
    ready_string = @binary.reverse
    sum = 0
    ready_string.chars.each_with_index do |char, index|
      if char == '1'
        sum += 2 ** index
      elsif char != '0'
        # set any illegal string to set sum to 0
        sum *= 0
        break
      end
    end
    return sum
  end

end

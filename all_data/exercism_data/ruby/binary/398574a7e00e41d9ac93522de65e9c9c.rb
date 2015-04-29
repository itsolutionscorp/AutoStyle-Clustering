class Binary
  def initialize(binary_string)
    @binary_string = binary_string
  end

  def to_decimal
    # Return zero if the binary_string is
    # not valid
    return 0 unless valid?

    sum = 0
    power_of_two = 0

    # Starting from the end of the string convert each bit
    # to its decimal value, and aggregate the sum.
    # Once the binary string is "" there's nothing more to
    # do, so we return the sum
    while !@binary_string.empty?
      sum += ((2 ** power_of_two) * @binary_string.slice!(-1).to_i)
      power_of_two += 1
    end

    # Return the sum
    sum
  end

  def valid?
    # Verify that the input is only string characters
    # "1" and "0". If there is any other character
    # return false.
    @binary_string.each_char do | char |
      return false unless char == "1" or char == "0"
    end
    true
  end
end

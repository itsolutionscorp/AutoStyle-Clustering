class Binary

  def initialize binary_number
    @binary_number = binary_number
  end

  def to_decimal
    return 0 unless is_valid?(@binary_number)

    right_to_left_position = (0..@binary_number.length).to_a.reverse
    current_reverse_position = 0

    @binary_number.chars.reduce(0) do |sum, binary_digit|
      current_reverse_position += 1
      Integer(binary_digit) * ( 2 ** right_to_left_position[current_reverse_position] ) + sum
     end
  end

  def is_valid? binary_number
    binary_number.scan(/[^01]/).length == 0
  end
end

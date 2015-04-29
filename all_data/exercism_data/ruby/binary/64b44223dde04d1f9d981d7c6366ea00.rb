class Binary

  attr_reader :binary_number

  def initialize binary_number
    @binary_number = binary_number
  end

  def to_decimal
    if is_a_binary?
      array_of_bits = binary_number.to_s.chars.reverse
      
      array_of_bits.each_with_index.reduce( 0 ) do |sum, ( bit, index )|
        sum + ( 2 ** index ) * bit.to_i
      end
    else
      0
    end
  end

private

  def is_a_binary?
    binary_number =~/\A[01]+\z/
  end

end

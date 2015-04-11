class Binary

  attr_reader :binary_string

  def initialize binary_string
    @binary_string = binary_string
  end

  def to_decimal
    digits_with_index.reduce( 0 ) do |sum, ( char, index )|
      sum + char * ( 2 ** index )
    end
  end

private
  
  def binary_array
     binary_string.match( /^[01]+$/ ).to_s.chars.map &:to_i
  end

  def digits_with_index
     binary_array.reverse_each.with_index
  end

end

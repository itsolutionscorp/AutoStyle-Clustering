class Binary

  attr_accessor :binary_string

  def initialize binary_string
    @binary_string = binary_string
  end

  def to_decimal
    binary_array.reverse_each.with_index.reduce( 0 ) do |sum, ( char, index )|
      sum + base_10_of( char, index )
    end
  end

private
  
  def binary_array
     binary_string.match( /^[01]+$/ ).to_s.chars.map &:to_i
  end

  def base_10_of char, index
    char * ( 2 ** index )
  end

end

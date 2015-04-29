class Trinary

  attr_reader :number

  def initialize trinary_string
    @number = trinary_string
  end

  def to_decimal
    digits_with_index.reduce( 0 ) do |sum, ( char, index )|
      sum + char * ( 3 ** index )
    end
  end

private
  
  def trinary_array
     number.match( /^[012]+$/ ).to_s.chars.map &:to_i
  end

  def digits_with_index
    trinary_array.reverse_each.with_index
  end

end

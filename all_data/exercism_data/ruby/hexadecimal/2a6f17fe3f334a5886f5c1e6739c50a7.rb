class Hexadecimal
  def initialize hex_string
    @hex = /^[\dabcdef]+$/ =~ hex_string ? hex_string : '0'
  end

  def to_decimal
    @hex.chars.reverse.each_with_index.reduce(0) do |total, (char, index)|
      total + VALUES[char] * 16**index
    end
  end
 
  private
    VALUES = {'0' => 0, '1' => 1, '2' => 2, '3' => 3, '4' => 4, '5' => 5, 
              '6' => 6, '7' => 7, '8' => 8, '9' => 9, 'a' => 10, 'b' => 11, 
              'c' => 12, 'd' => 13, 'e' => 14, 'f' => 15 }
end

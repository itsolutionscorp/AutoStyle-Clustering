class Trinary
  ASCII_ZERO = '0'.ord

  attr_accessor :trinary

  def initialize trinary
    self.trinary = trinary
  end

  def to_decimal
    reverse_bytes_with_index.reduce(0) do |base10,(char,index)|
      digit = char - ASCII_ZERO

      return 0 unless digit.between? 0,2

      base10 + digit * 3**index
    end
  end

  private

  def reverse_bytes_with_index
    trinary.bytes.reverse_each.with_index
  end
end

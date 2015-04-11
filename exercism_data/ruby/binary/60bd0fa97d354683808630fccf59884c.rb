require 'benchmark'

class Binary < String
  ASCII_ZERO = '0'.ord

  def to_decimal
    bytes.reverse_each.with_index.reduce(0) do |base10,(byte,i)|
      digit = byte - ASCII_ZERO

      return 0 unless digit.between? 0,1

      base10 + ( digit * 2**i )
    end
  end
end

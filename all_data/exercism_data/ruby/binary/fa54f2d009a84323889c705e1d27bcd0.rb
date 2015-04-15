class Binary
  ASCII_ZERO = '0'.ord

  attr_accessor :binary

  def initialize binary
    self.binary = binary
  end

  def to_decimal
    binary.bytes.reverse_each.with_index.reduce(0) do |base10,(byte,i)|
      digit = byte - ASCII_ZERO

      return 0 unless digit.between? 0,1

      base10 + ( digit * 2**i )
    end
  end
end

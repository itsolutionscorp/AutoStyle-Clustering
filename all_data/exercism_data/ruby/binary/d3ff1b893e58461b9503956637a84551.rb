class Binary < String
  ASCII_ZERO = '0'.ord

  def to_decimal
    max_i = length - 1

    each_byte.with_index.reduce(0) do |base10,(byte,i)|
      digit = byte - ASCII_ZERO

      return 0 unless digit.between? 0,1

      base10 + ( digit * 2**(i - max_i).abs )
    end
  end
end

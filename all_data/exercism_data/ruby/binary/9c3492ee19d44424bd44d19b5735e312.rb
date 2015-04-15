class Binary
  def initialize(big_endian_bin_str)
    # Store as Little-endian:
    #   The least significant byte (LSB) value, is at the lowest address.
    @bin = big_endian_bin_str.reverse
  end

  def to_decimal
    return 0 unless binary_string? @bin
    bit_list.each_with_index.reduce(0) { |sum, (bit_str, index)|
      sum + (bit_str.to_i << index)
    }
  end

  private

  def bit_list
    @bin.chars
  end

  def binary_string?(input)
    input =~ /\A[01]+\Z/
  end
end

class Trinary
  def initialize(big_endian_trin_str)
    # Store as Little-endian:
    #   The least significant byte (LSB) value, is at the lowest address.
    @trin = big_endian_trin_str.reverse
  end

  def to_decimal
    return 0 unless trinary_string? @trin
    trit_list.each_with_index.reduce(0) { |sum, (trit_str, index)|
      sum + (trit_str.to_i * 3**index)
    }
  end

  private

  def trit_list
    @trin.chars
  end

  def trinary_string?(input)
    input =~ /\A[012]+\Z/
  end
end

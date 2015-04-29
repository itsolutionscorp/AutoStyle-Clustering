class Binary < Struct.new(:value)
  def to_decimal
    # Ensure all characters are either 0's or 1's
    return 0 unless value =~ /^(0|1)+$/

    exp = -1
    value.chars.reverse.reduce(0) { |sum, v| exp += 1 ; sum += v.to_i * 2 ** exp }
  end
end

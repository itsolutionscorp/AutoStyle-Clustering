class Binary
  def initialize binary_string
    @binary_string = binary_string
  end

  def to_decimal
    return 0 if @binary_string =~ /[^01]/

    value = 0
    @binary_string.reverse.chars.map.with_index{|char, idx|
      value += 2**idx if char == ?1
    }
    value
  end
end

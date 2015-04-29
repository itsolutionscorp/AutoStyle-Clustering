class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    @binary.chars.reverse_each.with_index.inject(0) do |decimal, (char, exponent)|
      decimal + (2 ** exponent) * char.to_i
    end
  end
end

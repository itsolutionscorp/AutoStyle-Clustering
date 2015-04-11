class Binary
  attr_reader :n

  def initialize(n)
    @n = n
  end

  def to_decimal
    return 0 unless n =~ /^[01]+$/

    n.reverse.each_char.with_index.reduce(0) do |memo, (char, index)|
      memo + char.to_i * 2**index
    end
  end
end

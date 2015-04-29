class Binary
    attr_reader :n

    def initialize n
      @n = n
    end

    def to_decimal
    return 0 if n !~ /\b[01]+\b/
    n.reverse.chars.map.with_index {
      |b, index| 2 ** index * b.to_i }.inject(:+)
  end
end

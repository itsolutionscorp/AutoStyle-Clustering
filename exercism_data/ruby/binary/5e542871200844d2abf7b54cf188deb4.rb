class Binary
  attr_reader :binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 if binary !~ /\A[01]+\Z/
    multiplier = 1
    acc = 0
    binary.chars.reverse_each do |char|
      acc += char.to_i * multiplier
      multiplier *= 2
    end
    acc
  end
end

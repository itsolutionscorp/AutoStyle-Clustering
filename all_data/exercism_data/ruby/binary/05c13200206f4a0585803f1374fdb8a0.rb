class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 if @binary.match(/[^01]/)

    @binary.split("")
           .reverse
           .map(&:to_i)
           .each_with_index
           .reduce(0) { |sum, (digit, index)| sum += digit * (2 ** index) }
  end
end

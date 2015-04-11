class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    digits = @binary.split("")
    return 0 unless digits.all? { |n| ("0".."1").cover?(n) }

    digits.reverse
          .map(&:to_i)
          .each_with_index
          .reduce(0) { |sum, (digit, index)| sum += digit * (2 ** index) }
  end
end

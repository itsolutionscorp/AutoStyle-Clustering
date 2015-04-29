class Binary
  VALID_FORMAT = /^\d+$/

  def initialize(binary)
    @binary = binary =~ VALID_FORMAT ? binary.reverse : "0"
  end

  def to_decimal
    @binary.each_char.with_index.reduce(0) { |sum, (char, index)| sum + char.to_i * 2 ** index }
  end
end

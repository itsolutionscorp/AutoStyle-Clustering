class Binary
  attr_reader :binary_string

  def initialize(binary_string)
    @binary_string = binary_string
  end

  def to_decimal
    return 0 unless valid?

    binary_string.chars.reverse_each.with_index.reduce(0) do |sum, (digit, i)|
      sum + (digit == "1" ? 2**i : 0)
    end
  end

  private

  def valid?
    binary_string =~ /^[01]+$/
  end
end

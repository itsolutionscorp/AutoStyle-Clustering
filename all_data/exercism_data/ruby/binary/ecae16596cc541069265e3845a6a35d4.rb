class Binary
  def initialize binary_string
    case binary_string
    when /\A[01]*\Z/
      @binary_string = binary_string
    else
      @binary_string = ''
    end
  end

  def to_decimal
    binary_decimals.reduce(0) do |result, binary_decimal|
      result * 2 + binary_decimal
    end
  end

  def binary_decimals
    @binary_string.chars.map(&:to_i)
  end
end

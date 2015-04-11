# Converts binary number to its decimal equivalent.
class Binary
  attr_reader :binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    decimal = 0
    if binary.to_i.to_s.length != binaries.length
      decimal
    else
      binaries.each_with_index do |bin, index|
        decimal += bin * 2**index
      end
      decimal
    end
  end

  def binaries
    binary.each_char.map(&:to_i).reverse
  end
end

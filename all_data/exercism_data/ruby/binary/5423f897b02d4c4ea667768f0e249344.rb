class Binary
  attr_reader :binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 if @binary !~ /^[01]*$/
    decimal = 0
    values = binary.chars.reverse
    values.each_with_index do |value, index|
      decimal += (value.to_i * 2**index)
    end
    return decimal
  end
end

class Binary
  attr_reader :binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    unless @binary =~ /^[01]*$/
      return 0
    else
      decimal = 0
      values = binary.chars.reverse
      values.each_with_index do |value, index|
        decimal += (value.to_i * 2**index)
      end
    end
    decimal
  end
end

class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    decimal = 0
    if !@binary.match(/[^0-1]/)
      @binary.reverse.split('').each_with_index do |digit, index|
        decimal += digit.to_i * (2 ** index)
      end
    end
    return decimal
  end

end

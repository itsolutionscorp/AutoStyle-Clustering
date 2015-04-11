class Binary
  def initialize(binary)
    @binary = binary.split("")
    @total = 0
  end

  def to_decimal
    @binary.reverse.each_with_index do |element, index|
      @total += 2**index unless element.to_i != 1
    end

    return @total
  end

end

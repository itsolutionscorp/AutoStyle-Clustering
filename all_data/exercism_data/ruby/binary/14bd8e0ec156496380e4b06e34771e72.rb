class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    decimal = 0
    if @binary.scan(/[2-9a-zA-Z]/) == []
    @binary.chars.reverse.each_with_index { |int, index|
      if int == "1"
      decimal += 2**(index)
      end
    }
    decimal
    else
    decimal
    end
  end

end

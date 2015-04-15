class Binary
  
  def initialize(binary)
    @digits = convert(binary)
  end

  def to_decimal
    decimal = 0
    @digits.reverse.each_with_index do |digit, idx|
      decimal += digit * 2**idx
    end
    decimal
  end

  private

    def convert(binary)
      return [0] if binary.to_i.to_s != binary 
      binary.scan(/[0,1]/).map(&:to_i)
    end

end

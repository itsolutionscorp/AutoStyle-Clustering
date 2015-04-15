class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    decimal = 0

    if @binary.match(/\A[0-9]{1,}\z/)
      @binary.chars.reverse.each_with_index do |element, index|
        decimal += 2**(index) if element == '1'
      end
    end

    decimal
  end

end

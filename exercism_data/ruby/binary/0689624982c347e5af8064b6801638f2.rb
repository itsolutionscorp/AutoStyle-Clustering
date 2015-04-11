class Binary
  attr_reader :bin
  def initialize(bin)
    @bin = bin
  end

  def to_decimal
    return 0 unless bin.match(/^[01]+$/)
    bin.reverse.chars.each_with_index.inject(0) do |decimal, (digit, index)|
      decimal += digit.to_i * (2**index)
    end
  end
end

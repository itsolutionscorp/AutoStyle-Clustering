class Trinary

  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    len = @trinary.length
    decimal = 0

    @trinary.chars.each_with_index do |num, index|
      decimal += (num.to_i * 3** (len-index-1))
    end

    return decimal

  end
end

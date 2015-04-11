class Binary

  attr_accessor :bin
  def initialize(bin)
    self.bin = bin
  end

  def to_decimal

    res = 0
    bin.chars.reverse.each_with_index do |pos, i|
      res += Integer(pos) * 2**i
    end
    return res
  rescue ArgumentError => e
    return 0
  end
end

class Binary

  attr_accessor :bin

  def initialize(bin)
    @bin = bin
  end

  def to_decimal
    res = 0
    bin.chars.reverse.map{|pos| Integer(pos)}.each_with_index do |pos, i|
      res += pos * 2**i
    end
    return res
  rescue ArgumentError
    return 0
  end
end

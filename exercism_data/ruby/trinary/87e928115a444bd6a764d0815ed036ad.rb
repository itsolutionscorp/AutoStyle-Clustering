class Trinary
  attr_reader :tri
  
  def initialize(trinary)
    @tri = parse(trinary)
  end

  def to_decimal
    tri.map.with_index do |digit, i|
      digit * (3 ** i)
    end.inject(&:+)
  end

  private

  def parse(trinary)
    trinary.reverse.chars.map {|digit| digit.to_i}
  end

end

class Trinary
  attr_reader :tri
  
  def initialize(trinary)
    @tri = parse(trinary)
  end

  def to_decimal
    tri.each_with_index.inject(0) do |sum, (digit, i)|
      sum + digit * (3 ** i)
    end
  end

  private

  def parse(trinary)
    trinary.reverse.chars.map {|digit| digit.to_i}
  end

end

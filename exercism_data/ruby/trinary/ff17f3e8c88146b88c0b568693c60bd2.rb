class Trinary
  attr_reader :tri
  
  def initialize(trinary)
    @tri = parse(trinary)
  end

  def to_decimal
    powers_of_three.map.with_index do |power, i|
      power * tri[i]
    end.inject(&:+)
  end

  private

  def powers_of_three
    powers = []
    tri.length.times do |i|
      powers << 3 ** i
    end
    powers
  end

  def parse(trinary)
    trinary.reverse.chars.map {|digit| digit.to_i}
  end

end

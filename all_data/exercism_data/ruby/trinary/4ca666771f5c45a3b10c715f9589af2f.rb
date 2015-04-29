class Trinary
  attr_reader :tri
  def initialize(tri)
    @tri = tri
  end

  def to_decimal
    powers_of_three.each_with_index.map do |power, i|
      power * tri.reverse.chars[i].to_i
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

end

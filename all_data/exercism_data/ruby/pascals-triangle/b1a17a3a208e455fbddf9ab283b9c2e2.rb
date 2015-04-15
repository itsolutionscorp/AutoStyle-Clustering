class Triangle

  attr_reader :size

  def initialize size
    @size = size
    @triangle = Array.new(size) { |i| Array.new(i) }
  end

  def rows
    (0...size).map(&method(:row))
  end

  private
  attr_accessor :triangle

  def row i
    (0..i).map do |k|
      pred = triangle[i-1]
      triangle[i][k] = ([0,i].include? k) ? 1 : pred[k-1].to_i + pred[k].to_i
    end
  end

end

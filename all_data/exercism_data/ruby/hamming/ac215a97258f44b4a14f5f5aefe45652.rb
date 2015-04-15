class Hamming
  def self.compute first_strand, second_strand
    first_strand.length.times.inject(0) do |distance, i|
      distance += point_distance(first_strand[i], second_strand[i])
    end
  end

  private
  def self.point_distance(x, y)
    y.nil? || y == x ? 0 : 1
  end
end

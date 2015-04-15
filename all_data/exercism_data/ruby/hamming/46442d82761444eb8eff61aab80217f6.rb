class Hamming
  def self.compute first, second
    shortest = [first.length, second.length].min
    shortest.times.inject(0) do |distance, i|
      distance += point_distance(first[i], second[i])
    end
  end

  private
  def self.point_distance(x, y)
    y == x ? 0 : 1
  end
end

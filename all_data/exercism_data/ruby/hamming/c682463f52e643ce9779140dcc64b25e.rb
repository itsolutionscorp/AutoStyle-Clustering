class Hamming
  def self.compute original, mutation
    original.split('').each_with_index.inject(0) do |distance, (o, i)|
      distance += point_distance(o, mutation[i])
    end
  end

  private
  def self.point_distance(o, m)
    return 0 if m.nil?
    o == m ? 0 : 1
  end
end

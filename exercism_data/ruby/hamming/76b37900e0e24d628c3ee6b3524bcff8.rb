class Hamming

  def self.compute(a, b)
    raise "String must have the same length" unless a.size==b.size
    sub_compute(a, b, 0)
  end

  private 
  def self.sub_compute(a, b, distance)
    return distance if a.nil? or b.nil?
    distance += (a[0] != b[0]) ? 1 : 0
    sub_compute(a[1..-1], b[1..-1], distance)
  end

end

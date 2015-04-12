class Hamming
  def compute a, b
    return nil unless a && b
    return nil unless a.length == b.length
    difference = 0
    (0..a.length).each do |i|
      difference += 1 unless a[i] == b[i]
    end
    return difference
  end
end

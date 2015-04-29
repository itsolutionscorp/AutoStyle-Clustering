class Hamming
  def self.compute(seg1, seg2)
    return 0 unless seg1.length == seg2.length
    strands = seg1.chars.zip(seg2.chars)
    strands.count{ |first, second| first != second }
  end
end

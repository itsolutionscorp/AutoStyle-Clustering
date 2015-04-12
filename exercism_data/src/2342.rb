class Hamming
  def compute(seg1, seg2)
    return 0 unless seg1.length == seg2.length
    seg1_array = seg1.split('')
    seg2_array = seg2.split('')
    strands = seg1_array.zip(seg2_array)
    strands.collect{ |e,f| 1 if e != f }.compact.inject(:+) || 0
  end
end

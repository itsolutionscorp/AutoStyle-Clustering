class Hamming

  def self.compute(a,b)
     b = b.chars
     l = a.length < b.length ? a.length : b.length
     a.chars.take(l).map.with_index {|v,i| v != b[i] ? 1 : 0}.inject {|sum,x| sum + x}
  end

end

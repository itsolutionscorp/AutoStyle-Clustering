class Hamming

  def self.compute(a,b)
    a = a.chars
    b = b.chars
    a.map.with_index {|e,i|
      (b[i].nil? or b[i] == e) ? 0 : 1
    }.inject(:+)
  end

end

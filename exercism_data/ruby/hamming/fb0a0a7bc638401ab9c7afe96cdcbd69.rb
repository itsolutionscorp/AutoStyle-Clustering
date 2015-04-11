class Hamming

  def self.compute(a,b)
    a = a.chars
    b = b.chars
    a.map.with_index {|char,index|
      (b[index].nil? or b[index] == char) ? 0 : 1
    }.inject(:+)
  end

end

class Hamming

  def self.compute(a,b)
    a[0...b.length].chars.zip(b.chars).map {|x,y| x==y ? 0 : 1}.reduce (:+)
  end
  
end

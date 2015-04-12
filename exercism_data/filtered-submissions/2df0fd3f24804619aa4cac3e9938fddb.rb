def compute(a,b)
    a.chars.zip(b.chars).map{|x, y| (x == y or x.nil? or y.nil?) ? 0 : 1}.reduce(0,:+)
  end
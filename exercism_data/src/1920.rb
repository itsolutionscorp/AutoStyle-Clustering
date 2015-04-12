def compute(a,b)
    a.split('').zip(b.split('')).map{|x, y| (x == y or x.nil? or y.nil?) ? 0 : 1}.reduce(0,:+)
  end
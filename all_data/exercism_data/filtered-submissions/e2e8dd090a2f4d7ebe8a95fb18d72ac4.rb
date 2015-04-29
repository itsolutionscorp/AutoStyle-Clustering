def compute(a,b)
    a.split('').zip(b.split('')).map {|x,y| x==y || x==nil || y == nil}.count(false)
  end
def compute(a,b)
    a.split("").zip(b.split(""))
    .inject(0){|x,y| x + if y.first == y.last then 0 else 1 end}
  end
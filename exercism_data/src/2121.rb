def compute (a,b)
    a.split(//).reject.with_index{|x,y| x==b.split(//)[y]}.count
  end
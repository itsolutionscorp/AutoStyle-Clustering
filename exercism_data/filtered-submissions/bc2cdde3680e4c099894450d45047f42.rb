def compute(str1, str2)
    a1, a2 = str1.split(//), str2.split(//)
    rval = a1.length <= a2.length ? a1.zip(a2).map{|x,y|x==y} : a2.zip(a1).map{|x,y|x==y}
    rval.select{|i| !i}.length
  end
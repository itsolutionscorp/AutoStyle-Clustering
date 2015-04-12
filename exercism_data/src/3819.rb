def compute(a,b)
    [a,b].map(&:chars).inject(&:zip).select{|x| x.first != x.last}.length
  end
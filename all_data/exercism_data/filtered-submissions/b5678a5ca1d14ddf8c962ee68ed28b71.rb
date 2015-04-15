def compute (orig,compare)
    dnapairs = orig.chars.zip(compare.chars)
    dnapairs.count{|x,y| x !=y }
  end
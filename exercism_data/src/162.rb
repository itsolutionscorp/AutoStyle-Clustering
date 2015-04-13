def compute(as, bs)
    as.chars.zip(bs.chars).select {|a,b| a && b and a != b}.count
  end
def compute(base,comp)
     base.length.times.count {|i| base[i] != comp[i] }
  end
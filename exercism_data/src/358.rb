def compute(base, test)
    [base.size,test.size].min.times.count { |i| base[i] != test[i] }
  end
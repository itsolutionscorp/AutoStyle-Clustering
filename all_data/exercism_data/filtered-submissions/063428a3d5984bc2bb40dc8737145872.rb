def compute(a, b)
    diff_count = 0
    [a.length,b.length].min.times do |i|
      diff_count += ((a[i]==b[i])?0:1)
    end
    diff_count
  end
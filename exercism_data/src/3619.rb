def compute (a,b)
    count_differences = 0
    0.upto([a.length, b.length].min-1) do |i|
      count_differences += 1 if a[i] != b[i]
    end
    return count_differences
  end
  
end
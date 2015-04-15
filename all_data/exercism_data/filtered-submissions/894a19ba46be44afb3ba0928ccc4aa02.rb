def compute(a, b)
    a.chars.each_index.inject(0) do |distance, i|
      a[i] == b[i] ? distance : distance + 1
    end
  end
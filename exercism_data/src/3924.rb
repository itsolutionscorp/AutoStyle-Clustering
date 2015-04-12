def compute(a,b)

    a = a[0, b.size]

    a.chars.each_with_index.count do |char, index|
      char != b[index]
    end
  end
def compute(a,b)
    a.chars.zip(b.chars).reduce(0) do |acc, pair|
      acc += 1 if pair[0] != pair[1]
      acc
    end
  end
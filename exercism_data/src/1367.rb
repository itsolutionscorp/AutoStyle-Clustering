def compute(a,b)
    n = [a.length, b.length].min - 1
    (0..n).reduce(0) do |score, i|
      score + (a[i]==b[i] ? 0 : 1)
    end
  end
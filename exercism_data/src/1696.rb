def compute(a, b)
    a.chars.zip(b.chars).count { |c1, c2| c1 != c2 }
  end
end
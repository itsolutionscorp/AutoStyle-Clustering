def compute(a, b)
      a.chars.zip(b.chars).count { |v1, v2| v1 != v2 }
    end
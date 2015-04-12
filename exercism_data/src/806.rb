def compute(a, b)
      a.each_char.with_index.count { |c, i| c && b[i] && (c != b[i]) }
    end
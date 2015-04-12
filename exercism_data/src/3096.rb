def compute(s, t)
    size = [s.size, t.size].min
    size.times.inject(0) do |errors, i|
      errors += s[i] == t[i] ? 0 : 1
    end
  end
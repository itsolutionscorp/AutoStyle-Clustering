def compute(s1, s2)
    s1.chars.zip(s2.chars).inject(0) do |cnt, (e1, e2)|
      cnt += (e1 == e2) || e2.nil? ? 0: 1
    end
  end
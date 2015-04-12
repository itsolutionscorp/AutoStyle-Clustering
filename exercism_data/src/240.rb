def compute(s, t)
      (0...s.length).count do |i|
        s[i] != t[i]
      end
  end
def compute(s,t)
    s.chars.zip(t.chars).map(&:compact).map(&:uniq).count { |p| p.size == 2 }
  end
end
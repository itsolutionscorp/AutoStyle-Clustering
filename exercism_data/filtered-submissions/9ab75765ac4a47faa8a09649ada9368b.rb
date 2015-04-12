def compute(s1, s2)
    shorter_s = s1.size < s2.size ? s1.size : s2.size
    (0...shorter_s).inject(0) { |count,i| s1[i] != s2[i] ? count+1 : count }
  end
end
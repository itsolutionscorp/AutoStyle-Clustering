def compute(s1, s2)
    paired = s1.split(//).zip(s2.split(//))
    paired.reduce(0) do |sum, pair|
      sum + pair.uniq.count() - 1
    end
  end
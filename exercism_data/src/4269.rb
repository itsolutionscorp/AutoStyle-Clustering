def compute(l, r)
    minlen = [l.length, r.length].min
    odds = 0
    minlen.times do |i|
      odds += 1 if l[i] != r[i]
    end
    return odds
  end
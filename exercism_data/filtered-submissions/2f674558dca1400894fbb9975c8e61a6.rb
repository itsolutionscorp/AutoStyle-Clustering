def compute(s, t)
    score = 0

    s.each_char.with_index do |c, i|

      score += 1 if t[i] && c != t[i]
    end
    score
  end
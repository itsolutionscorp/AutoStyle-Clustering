def compute(s, t)
    if s == t
      0
    else
      matches = 0

      s.length.times do |i|
        if s[i] != t[i]
          matches +=1
        end
      end

      matches
    end
  end
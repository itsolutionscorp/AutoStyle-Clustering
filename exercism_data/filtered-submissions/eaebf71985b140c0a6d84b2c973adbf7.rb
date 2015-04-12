class Hamming
  def compute(s,t)
    s = s.split(//)
    t = t.split(//)

    if s.length == t.length
      distance = 0
      s.length.to_i.times do |i|
        unless s[i] == t[i]
          distance += 1
        end
      end
    else
      raise
    end

    return distance
  end
end

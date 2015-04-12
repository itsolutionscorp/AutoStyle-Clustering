class Hamming
  def compute(s,t)
    s = s.chars
    t = t.chars

    if s.length == t.length
      distance = 0
      s.length.times do |i|
        unless s[i] == t[i]
          distance += 1
        end
      end
    else
      raise 'strings must be equal length'
    end

    distance
  end
end

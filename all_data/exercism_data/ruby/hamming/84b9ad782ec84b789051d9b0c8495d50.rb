class Hamming
  def self.compute(s, t)
    if s.length > t.length
      self.compute(t, s)
    else
      (0...s.length).inject(0) do |dist, index|
        if s[index] == t[index]
          dist
        else
          dist + 1
        end
      end
    end
  end
end

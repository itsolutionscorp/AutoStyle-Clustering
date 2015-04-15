class Hamming
  def self.compute(s,t)
    s = s.chars
    t = t.chars

    raise 'strings must be equal length' unless s.length == t.length

    distance = 0
    s.length.times do |i|
      unless s[i] == t[i]
        distance += 1
      end
    end

    distance
  end
end

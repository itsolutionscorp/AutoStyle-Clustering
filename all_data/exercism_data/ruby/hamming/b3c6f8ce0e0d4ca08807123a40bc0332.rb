class Hamming
  def self.compute(s1, s2)
    dist = 0
    short, long = [s1, s2].sort
    striped = long[0..(short.length-1)]
    striped.chars.zip(short.chars).each { |a,b| dist += 1 if a != b }
    return dist
  end

end

class Hamming

  def self.compute x, y
    distance = 0
    as, bs = x.to_s, y.to_s
    short, long = [as, bs].sort
    long.chars.zip(short.chars).each {|ac, bc| distance += 1 if ac != bc }
    distance
  end
end
